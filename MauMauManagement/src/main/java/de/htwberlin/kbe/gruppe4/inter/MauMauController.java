package de.htwberlin.kbe.gruppe4.inter;

import java.util.ArrayList;
import java.util.List;
import com.google.inject.Inject;

import de.htwberlin.kbe.gruppe4.impl.CLIServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.GameServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.RulesServiceImpl;

public class MauMauController {
    private final CLIService cli;
    private final GameService gameService;

    @Inject
    public MauMauController(CLIService cli, GameService gameService) {
        this.cli = cli;
        this.gameService = gameService;
    }

    public void startGame(List<String> playerNames) {
        gameService.setRules(cli.getRule("draw two on seven"), cli.getRule("choose suit on jack"),
                cli.getRule("reverse on ace"));
        gameService.startGame();
        Card lead = gameService.getLeadCard();
        cli.displayLead(lead.getSuit(), lead.getRank());
        gameService.addCardToTable(lead);
        while (!gameService.isGameOver()) {
            Player player = gameService.getPlayers().get(gameService.getCurrentPlayer());
            cli.displayHand(player.getName(), player.getHand());
            String input = cli.getPlayOrDraw();
            playTurn(player, lead, input, 0);
        }
        cli.announceWinner(gameService.getPlayers().get(gameService.getCurrentPlayer()).getName());
    }

    public void playTurn(Player player, Card lead, String input, int turns) {
        cli.displayHand(player.getName(), player.getHand());
        cli.displayLead(lead.getSuit(), lead.getRank());

        int noOfTurns = 1;
        if (input.equals("d")) {
            Card played = gameService.drawCard(player);
            cli.displayDraw(played.getSuit(), played.getRank());
        } else {
            int index = Integer.parseInt(input);
            Card played = gameService.playCard(player, index);
            if (played == null || !gameService.isCardValid(played, lead)) {
                if (turns > 5) {
                    cli.announceError();
                    return;
                }
                cli.announceInvalid();
                playTurn(player, lead, getPlayOrDraw(), turns + 1);
            } else {
                cli.displayPlay(played.getSuit(), played.getRank());
                if (gameService.isDrawTwoOnSeven() && played.getRank() == Card.Rank.SEVEN) {
                    noOfTurns++;
                }
                if (gameService.isChooseSuitOnJack() && played.getRank() == Card.Rank.JACK) {
                }
                if (gameService.isReverseOnAce() && played.getRank() == Card.Rank.ACE) {
                    noOfTurns = -1;
                }
                gameService.addCardToTable(played);
                gameService.updateCurrentPlayer(noOfTurns);
            }
        }
    }

    public String getPlayOrDraw() {
        cli.displayPlayOrDraw();
        return cli.getPlayOrDraw();
    }

    public static void main(String [] args){
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        CLIServiceImpl cliServiceImpl = new CLIServiceImpl();
        DeckServiceImpl deckServiceImpl = new DeckServiceImpl();
        RulesServiceImpl rulesServiceImpl = new RulesServiceImpl();
        PlayerServiceImpl playerServiceImpl = new PlayerServiceImpl();
        GameServiceImpl gameServiceImpl = new GameServiceImpl(names, deckServiceImpl, rulesServiceImpl, playerServiceImpl);
        MauMauController controller = new MauMauController(cliServiceImpl, gameServiceImpl);
        // Injector injector = Guice.createInjector(new MauMauModule());
        // MauMauController controller = injector.getInstance(MauMauController.class);
        controller.startGame(names);
    }
}