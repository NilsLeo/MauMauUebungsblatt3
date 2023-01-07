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
    void updateCurrentPlayer(int noOfTurns);
    List<Player> getPlayers();
    int getCurrentPlayer();
    Card.Suit getLeadSuit();
    Card.Rank getLeadRank();
    boolean isGameOver();
    boolean isCardValid(Card card, Card lead);
    boolean isDrawTwoOnSeven();
    boolean isChooseSuitOnJack();
    boolean isReverseOnAce();
  }
  