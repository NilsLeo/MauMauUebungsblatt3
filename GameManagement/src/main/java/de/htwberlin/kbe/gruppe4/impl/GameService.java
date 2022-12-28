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

    @Inject
    public GameService(List<String> names, CLIServiceImpl cli, DeckService deckService, RulesService rulesService,
            PlayerService playerService) {
        this.cli = cli;
        this.players = new ArrayList<>();
        for (String name : names) {
            this.players.add(new Player(name));
        }
        this.deck = new Deck();
        this.table = new ArrayList<>();
        this.rules = new Rules();
        this.currentPlayer = 0;
        this.deckService = deckService;
        this.rulesService = rulesService;
        this.playerService = playerService;
    }

    public void play() {
        cli.displayRules();
        rules.setDrawTwoOnSeven(cli.getRule("draw two on seven"));
        rules.isDrawTwoOnSeven();
        rules.setChooseSuitOnJack(cli.getRule("choose suit on jack"));
        rules.setReverseOnAce(cli.getRule("reverse on ace"));
        deckService.shuffle(deck);
        for (Player player : players) {
            playerService.dealHand(player, deck);
        }
        Card lead = deckService.deal(deck);
        table.add(lead);
        Card.Suit leadSuit = lead.getSuit();
        Card.Rank leadRank = lead.getRank();
        while (true) {
            Player player = players.get(currentPlayer);
            int noOfTurns = 1;
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
                            Player nextPlayer = players.get((currentPlayer + 1) % players.size()); // get next player in
                                                                                                   // the list
                            Card drawn1 = deckService.deal(deck); // draw first card
                            Card drawn2 = deckService.deal(deck); // draw second card
                            playerService.draw(nextPlayer, drawn1); // add first card to next player's hand
                            playerService.draw(nextPlayer, drawn2); // add second card to next player's hand
                            leadSuit = played.getSuit(); // update lead suit
                            leadRank = played.getRank(); // update lead rank
                            cli.announcePlay(player.getName(), played);
                            cli.announceDrawTwoCards();
                        }
                        if (rules.isChooseSuitOnJack() && played.getRank() == Card.Rank.JACK) { // check if "choose suit
                                                                                                // on
                            // jack" rule is enabled and
                            // played card is a jack
                            cli.displaySuits(); // display list of possible suits
                            String chosenSuit = cli.getSuitChoice(); // prompt player to choose a suit
                            rules.setChosenSuitTemporarilyEnabled(true);
                            rules.setSuit(Card.Suit.valueOf(chosenSuit.toUpperCase()));
                            leadSuit = played.getSuit(); // update lead suit
                            leadRank = played.getRank(); // update lead rank
                            cli.announcePlay(player.getName(), played);
                            cli.announceChosenSuit(Card.Suit.valueOf(chosenSuit.toUpperCase()));

                        }

                        if (played.getRank() == Card.Rank.ACE) {
                            leadSuit = played.getSuit(); // update lead suit
                            leadRank = played.getRank(); // update lead rank
                            noOfTurns++;
                            cli.announcePlayAgainOnAce(player.getName(), played);
                        } else {
                            cli.announcePlay(player.getName(), played);
                            leadSuit = played.getSuit();
                            leadRank = played.getRank();
                            rules.setChosenSuitTemporarilyEnabled(false);

                        }
                    }
                }
            }
            if (player.getHand().isEmpty()) {
                cli.announceWinner(player.getName());
                break;
            }
            noOfTurns--;
            if(noOfTurns==0){ currentPlayer++;}
           

            if (currentPlayer >= players.size()) {
                currentPlayer = 0;
            }

            // shuffle deck, when there are 2 cards or less left
            if (deck.getCards().size() <= 2) {
                List<Card> newDeck = new ArrayList<>();
                for (Card card : table) {
                    if (!card.equals(lead)) {
                        newDeck.add(card);
                        table.remove(card);
                    }
                }
                for (Card card : deck.getCards()) {
                    newDeck.add(card);
                }

                deck.setCards(newDeck);
                deckService.shuffle(deck);
            }

        }

    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GameModule());

        CLIServiceImpl cli = injector.getInstance(CLIServiceImpl.class);
        DeckServiceImpl deckService = injector.getInstance(DeckServiceImpl.class);
        RulesServiceImpl rulesService = injector.getInstance(RulesServiceImpl.class);
        PlayerServiceImpl playerService = injector.getInstance(PlayerServiceImpl.class);
        playerService.setDeckService(deckService);
        List<String> names = cli.getPlayerNames();
        GameService game = new GameService(names, cli, deckService, rulesService, playerService);
        game.play();
    }
}