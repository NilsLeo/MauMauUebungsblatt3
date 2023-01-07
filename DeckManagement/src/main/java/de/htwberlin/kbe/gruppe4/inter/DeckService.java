package de.htwberlin.kbe.gruppe4.inter;
import java.util.ArrayList;
import java.util.List;

public interface DeckService {
    void shuffle(Deck deck);

    ArrayList<Card> dealHand(Deck deck);

    Card deal(Deck deck);
}
