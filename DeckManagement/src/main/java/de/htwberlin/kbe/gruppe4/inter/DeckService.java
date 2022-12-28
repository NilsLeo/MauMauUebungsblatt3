package de.htwberlin.kbe.gruppe4.inter;
import java.util.List;

public interface DeckService {
    void shuffle(Deck deck);

    List<Card> dealHand(Deck deck);

    Card deal(Deck deck);
}
