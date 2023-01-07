package de.htwberlin.kbe.gruppe4.inter;

import java.util.List;

public interface GameService {
    void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce);
    void startGame();
    Card getLeadCard();
    void addCardToTable(Card card);
    Card drawCard(Player player);
    Card playCard(Player player, int index);
    boolean isCardValid(Card card);
    void setCurrentPlayer(int noOfTurns);
    List<Player> getPlayers();
    int getCurrentPlayer();
    Card.Suit getLeadSuit();
    Card.Rank getLeadRank();
    boolean isGameOver();
    boolean isCardValid(Card card, Card lead);
    boolean isDrawTwoOnSeven();
    boolean isChooseSuitOnJack();
    boolean isReverseOnAce();
    void setPlayers(List<String> names);
    void setDeckService(DeckService deckService);
    Deck getDeck();
    Rules getRules();
    List<Card> getTable();
    void setReversed(boolean reversed);
    boolean isReversed();
    void setSuitChoice(Card.Suit suit);
  }
  