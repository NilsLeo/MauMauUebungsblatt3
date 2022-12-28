package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Guice;
import javax.inject.Inject;
import com.google.inject.Injector;
import de.htwberlin.kbe.gruppe4.inter.GameModule;
import de.htwberlin.kbe.gruppe4.inter.*;

import java.util.ArrayList;
import java.util.List;
public class GameService {
    private final CLIServiceImpl cli;
    DeckService deckService;

    RulesService rulesService;

    PlayerService playerService;

    private final List<Player> players;
    private final Deck deck;
    private final List<Card> table;
    private final Rules rules;
    private int currentPlayer;
    private boolean reversed;
@Inject
    public GameService(List<String> names, CLIServiceImpl cli, DeckService deckService, RulesService rulesService, PlayerService playerService) {
        this.cli = cli;
        this.players = new ArrayList<>();
        for (String name : names) {
            this.players.add(new Player(name));
        }
        this.deck = new Deck();
        this.table = new ArrayList<>();
        this.rules = new Rules();
        this.currentPlayer = 0;
        this.reversed = false;
        this.deckService = deckService;
        this.rulesService = rulesService;
        this.playerService = playerService;
    }

    public void play() {
        cli.displayRules();
        rules.setDrawTwoOnSeven(cli.getRule("draw two on seven"));
        rules.setChooseSuitOnJack(cli.getRule("choose suit on jack"));
        rules.setReverseOnAce(cli.getRule("reverse on ace"));
        for (Player player : players) {
            playerService.dealHand(player, deck);
        }
        Card lead = deckService.deal(deck);
        table.add(lead);
        Card.Suit leadSuit = lead.getSuit();
        Card.Rank leadRank = lead.getRank();
        while (true) {
            Player player = players.get(currentPlayer);
            cli.displayHand(player.getName(), player.getHand());
            cli.displayLead(leadSuit, leadRank);
            Card played = null;
            String input = null;
            while (played == null) {
                while (true) {
                    input = cli.getPlayOrDraw();
                    if (input.equals("d") || input.matches("\\d+")) {
                        break;
                    }
                    cli.announceInvalid();
                }
                if (input.equals("d")) {
                    played = deckService.deal(deck);
                    playerService.draw(player, played);
                } else {
                    int index = Integer.parseInt(input);
                    played = playerService.play(player, index, leadSuit, leadRank);
                    if (played != null && rulesService.isCardValid(played, leadSuit, leadRank, rules)) {
                        if (rules.isDrawTwoOnSeven() && played.getRank() == Card.Rank.SEVEN) {
                            cli.announcePlay(player.getName(), played);
                            playerService.draw(player, deckService.deal(deck));
                            playerService.draw(player, deckService.deal(deck));
                        } else {
                            cli.announcePlay(player.getName(), played);
                            leadSuit = played.getSuit();
                            leadRank = played.getRank();
                        }
                    }
                }
            }
            if (player.getHand().isEmpty()) {
                cli.announceWinner(player.getName());
                break;
            }
            if (rules.isReverseOnAce() && played.getRank() == Card.Rank.ACE) {
                reversed = !reversed;
            }
            if (reversed) {
                currentPlayer--;
                if (currentPlayer < 0) {
                    currentPlayer = players.size() - 1;
                }
            } else {
                currentPlayer++;
                if (currentPlayer >= players.size()) {
                    currentPlayer = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        CLIServiceImpl cli = new CLIServiceImpl();
        DeckService deckService = new DeckServiceImpl();
        RulesService rulesService = new RulesServiceImpl();
        PlayerService playerService = new PlayerServiceImpl();
        playerService.setDeckService(deckService);
        List<String> names = cli.getPlayerNames();
        GameService game = new GameService(names, cli, deckService, rulesService, playerService);
        game.play();
    }
}