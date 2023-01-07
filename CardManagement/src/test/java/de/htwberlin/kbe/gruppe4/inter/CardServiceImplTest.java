package de.htwberlin.kbe.gruppe4.inter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.htwberlin.kbe.gruppe4.impl.CardServiceImpl;
public class CardServiceImplTest {
    CardServiceImpl cardService = new CardServiceImpl();

    @Test
    public void testCreateDeck() {
        List<Card> deck = cardService.createDeck();
        assertEquals(32, deck.size());

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                assertTrue(deck.contains(new Card(suit, rank)));
            }
        }
    }
}