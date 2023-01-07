package de.htwberlin.kbe.gruppe4.inter;

import java.util.List;

public interface CLIService {
    void displayRules();

    boolean getRule(String name);

    void displayHand(String name, List<Card> hand);

    void displayLead(Card.Suit suit, Card.Rank rank);

    String getPlayOrDraw();

    void announceInvalid();

    void announcePlay(String name, Card card);
    void announcePlayAgainOnAce(String name, Card card);

    void announceWinner(String name);

    List<String> getPlayerNames();
    void announceDrawTwoCards();
    void displaySuits();
    String getSuitChoice();
    void announceChosenSuit(Card.Suit suit);
    void displayPlayOrDraw();
    void displayDraw(Card.Suit suit, Card.Rank rank);
    void displaySuitChoice();
    void displayPlay(Card.Suit suit, Card.Rank rank);
    void announceError();

}
