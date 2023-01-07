package de.htwberlin.kbe.gruppe4.impl;
import javax.inject.Inject;
import de.htwberlin.kbe.gruppe4.inter.*;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {
    private static final Logger logger = Logger.getLogger(GameService.class);
    private DeckService deckService;
    RulesService rulesService;
    PlayerService playerService;
    private final List<Player> players;
    private final Deck deck;
    private List<Card> table;
    private final Rules rules;
    private int currentPlayer;


    @Inject
    public GameServiceImpl(DeckService deckService, RulesService rulesService,
            PlayerService playerService) {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.table = new ArrayList<>();
        this.rules = new Rules();
        this.currentPlayer = 0;
        this.deckService = deckService;
        this.rulesService = rulesService;
        this.playerService = playerService;
    }

    @Override
    public void setPlayers(List<String> names){
        for (String name : names) {
            this.players.add(new Player(name));
        }
    }

    @Override
    public void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce) {
        rules.setDrawTwoOnSeven(drawTwoOnSeven);
        rules.setChooseSuitOnJack(chooseSuitOnJack);
        rules.setReverseOnAce(reverseOnAce);
        logger.info("Rules for the game have been set: draw two on seven = " + drawTwoOnSeven + 
                ", choose suit on jack = " + chooseSuitOnJack + 
                ", reverse on ace = " + reverseOnAce);
    }

    @Override
    public void startGame() {
        deckService.shuffle(deck);
        table.add(deckService.deal(deck));
        for (Player player : players) {
            playerService.dealHand(player, deck);
        }
        logger.info("Game started");
    }

    @Override
    public Card getLeadCard() {
        return table.get(table.size() - 1);


    }

    @Override
    public void addCardToTable(Card card) {
        table.add(card);
        logger.info(card.getRank() + " of " + card.getSuit() + " was placed on table.");
        logger.debug("Top card is the " + getLeadRank() + " of " + getLeadSuit());
        // removes every card but the lead card and adds them back to deck and shuffles the deck every time a card is placed
        table = deckService.renewDeckFromTable(deck, table);
        
    }

    @Override
    public Card drawCard(Player player) {
        Card card = deckService.deal(deck);
        playerService.draw(player, card);

        logger.info(player + " drew a " + card.getRank() + " of " + card.getSuit());

        return card;
    }

    @Override
    public Card playCard(Player player, int index) {
        return playerService.play(player, index, getLeadSuit(), getLeadRank());
    }

    @Override
    public boolean isCardValid(Card card) {
        return rulesService.isCardValid(card, getLeadSuit(), getLeadRank(), rules);
    }

    @Override
    public void setCurrentPlayer(int noOfTurns) {
        currentPlayer += noOfTurns;
        if (currentPlayer < 0) {
            currentPlayer = players.size() - 1;
        } else if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
        

    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public int getCurrentPlayer() {

        return currentPlayer;
    }

    @Override
    public Card.Suit getLeadSuit() {
        return table.get(table.size() - 1).getSuit();
    }

    @Override
    public Card.Rank getLeadRank() {
        return table.get(table.size() - 1).getRank();
    }

    @Override
    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCardValid(Card card, Card lead) {
        return rulesService.isCardValid(card, getLeadSuit(), getLeadRank(), rules);
    }

    @Override
    public boolean isDrawTwoOnSeven() {
        return rules.isDrawTwoOnSeven();
    }

    @Override
    public boolean isChooseSuitOnJack() {
        return rules.isChooseSuitOnJack();
    }

    @Override
    public boolean isReverseOnAce() {
        return rules.isReverseOnAce();
    }
    @Override
    public boolean isReversed() {
        return rules.isReversed();
    }
    @Override
    public void setReversed(boolean reversed) {
        rules.setReversed(reversed);;
    }

    @Override
    public Deck getDeck(){
        return deck;
    }
    @Override
    public Rules getRules(){
        return rules;
    }

    @Override
    public void setDeckService(DeckService deckService) {
        this.deckService = deckService;
        
    }

    @Override
    public List<Card> getTable() {
        
        return table;
    }

    @Override
    public void setSuitChoice(Card.Suit suit) {
        rules.setSuit(suit);
    }
}