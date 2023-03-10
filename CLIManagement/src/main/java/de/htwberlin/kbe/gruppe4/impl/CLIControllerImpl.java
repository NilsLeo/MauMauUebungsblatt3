package de.htwberlin.kbe.gruppe4.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.htwberlin.kbe.gruppe4.config.MauMauModule;
import de.htwberlin.kbe.gruppe4.inter.CLIService;
import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.GameService;
import de.htwberlin.kbe.gruppe4.inter.CLIController;
import de.htwberlin.kbe.gruppe4.inter.Player;

public class CLIControllerImpl implements CLIController {
    private final CLIService cli;
    private final GameService gameService;
    int nextPlayerDraws = 0;
    boolean rememberedToSayMauMau = false;

    @Inject
    public CLIControllerImpl(CLIService cli, GameService gameService) {
        this.cli = cli;
        this.gameService = gameService;
    }

    public List<String> setNames() {
        return cli.getPlayerNames();
    }

    public void startGame() {
        gameService.setPlayers(cli.getPlayerNames());
        gameService.setRules(cli.getRule("draw two on seven"), cli.getRule("choose suit on jack"), cli.getRule("reverse on ace"));
        gameService.startGame();
        gameService.addCardToTable(gameService.getLeadCard());
        while (!gameService.isGameOver()) {
            cli.displayLead(gameService.getLeadCard().getSuit(), gameService.getLeadCard().getRank());
            Player player = gameService.getPlayers().get(gameService.getCurrentPlayer());
            if (nextPlayerDraws != 0) {
                for (int i = 0; i < nextPlayerDraws; i++) {
                    drawCard(player);

                }
                nextPlayerDraws = 0;
            }
            cli.displayHand(player.getName(), player.getHand());
            cli.displayPlayOrDraw();
            String input = cli.getPlayOrDraw();
            playTurn(player, gameService.getLeadCard(), input, 0);
        }
        cli.announceWinner(gameService.getPlayers().get(gameService.getCurrentPlayer()).getName());
    }

    public void playTurn(Player player, Card lead, String input, int turns) {
        // cli.displayLead(lead.getSuit(), lead.getRank());
        int noOfTurns = 1;
        try {
            if (input.equals("d")) {
                drawCard(player);
                gameService.setCurrentPlayer(noOfTurns);
            } else {
                int index = 0;
                if (input.contains("m")) {
                    if ((player.getHand().size() == 2)) {
                        cli.announceMauMau();
                        rememberedToSayMauMau = true;
                        Matcher matcher = Pattern.compile("\\d+").matcher(input);
                        matcher.find();
                        index = Integer.valueOf(matcher.group())-1;
                    } else {
                        cli.announceInvalidMauMauCall();
                    }
                } else {

                    index = Integer.parseInt(input)-1;
                }
                Card played = gameService.playCard(player, index);
                if (played == null || !gameService.isCardValid(played, lead)) {
                    cli.announceInvalid();
                    playTurn(player, lead, getPlayOrDraw(), turns + 1);
                    lead = played;
                } else if(gameService.isCardValid(played, lead)) {
                    cli.displayPlay(played.getSuit(), played.getRank());
                    gameService.addCardToTable(played);

                    lead = gameService.getLeadCard();
                    if (gameService.isDrawTwoOnSeven() && played.getRank() == Card.Rank.SEVEN) {
                        nextPlayerDraws += 2;
                    } 

                    if (gameService.isChooseSuitOnJack() && played.getRank() == Card.Rank.JACK) {
                        cli.displaySuitChoice();
                        cli.displaySuits();
                        Card.Suit choice = cli.getSuitChoice();
                        gameService.setSuitChoice(choice);
                        cli.announceChosenSuit(choice);
                    }

                    if (gameService.isReverseOnAce() && played.getRank() == Card.Rank.ACE) {
                        gameService.setReversed(!gameService.isReversed());
                    }
                    // 2 Karten Strafziehen
                    if (player.getHand().size() == 1 && !rememberedToSayMauMau) {
                        cli.announceForgotToSayMauMau();
                        drawCard(player);
    
                        drawCard(player);
                    }
                    if (player.getHand().size() == 1) {
                        noOfTurns = -1;
                    }
                    gameService.setCurrentPlayer(noOfTurns);
                }
            }
        } catch (Exception e) {
            cli.announceInvalid();
        }
    }

    public String getPlayOrDraw() {
        cli.displayPlayOrDraw();
        return cli.getPlayOrDraw();
    }

    public void drawCard(Player player){
        if(!gameService.hasCardsLeft()){
            cli.announceNoCardsLeft();
        }
        else{

            Card drawnCard = gameService.drawCard(player);
            cli.displayDraw(drawnCard.getSuit(), drawnCard.getRank());
        }
    }
}