package de.htwberlin.kbe.gruppe4.impl;
import javax.inject.Inject;
import de.htwberlin.kbe.gruppe4.inter.*;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static final Logger logger = Logger.getLogger(GameService.class);
    private DeckService deckService;
    RulesService rulesService;
    PlayerService playerService;
    private final List<Player> players;
    private final Deck deck;
    private final List<Card> table;
    private final Rules rules;
    private int currentPlayer;


    @Inject
    public GameService(List<String> names, DeckService deckService, RulesService rulesService,
            PlayerService playerService) {
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
    public void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce) {
        rules.setDrawTwoOnSeven(drawTwoOnSeven);
        rules.setChooseSuitOnJack(chooseSuitOnJack);
        rules.setReverseOnAce(reverseOnAce);
        logger.info("Rules for the game have been set: draw two on seven = " + drawTwoOnSeven + 
                ", choose suit on jack = " + chooseSuitOnJack + 
                ", reverse on ace = " + reverseOnAce);
    }

    public void startGame() {
        deckService.shuffle(deck);
        for (Player player : players) {
            playerService.dealHand(player, deck);
        }
    }

    public Card getLeadCard() {
        return deckService.deal(deck);
    }

    public void addCardToTable(Card card) {
        table.add(card);
    }

    public Card drawCard(Player player) {
        Card card = deckService.deal(deck);
        playerService.draw(player, card);
        return card;
    }

    public Card playCard(Player player, int index) {
        return playerService.play(player, index, getLeadSuit(), getLeadRank());
    }

    public boolean isCardValid(Card card) {
        return rulesService.isCardValid(card, getLeadSuit(), getLeadRank(), rules);
    }

    public void updateCurrentPlayer(int noOfTurns) {
        currentPlayer += noOfTurns;
        if (currentPlayer < 0) {
            currentPlayer = players.size() - 1;
        } else if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Card.Suit getLeadSuit() {
        return table.get(0).getSuit();
    }

    public Card.Rank getLeadRank() {
        return table.get(0).getRank();
    }

    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public boolean isCardValid(Card card, Card lead) {
        if (card.getSuit() == lead.getSuit()) {
            return true;
        } else if (card.getRank() == lead.getRank()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isDrawTwoOnSeven() {
        return rules.isDrawTwoOnSeven();
    }
    
    public boolean isChooseSuitOnJack() {
        return rules.isChooseSuitOnJack();
    }
    
    public boolean isReverseOnAce() {
        return rules.isReverseOnAce();
    }
}