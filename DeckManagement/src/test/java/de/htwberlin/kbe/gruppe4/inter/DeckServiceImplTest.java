package de.htwberlin.kbe.gruppe4.inter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Deck;
import de.htwberlin.kbe.gruppe4.inter.DeckService;

@ExtendWith(MockitoExtension.class)
public class DeckServiceImplTest {

    @Mock
    private Deck deck;

    @Mock
    private Card card;

    private DeckService deckService;

    @BeforeEach
    public void setUp() {
        deckService = new DeckServiceImpl();
    }

    // @Test
    // public void testShuffle() {
    //     List<Card> cards = new ArrayList<>();
    //     cards.add(card);
    
    //     when(deck.getCards()).thenReturn(cards);
    
    //     deckService.shuffle(deck);
    
    //     // Verify that the shuffle method was called on the cards list
    //     verify(cards, times(1)).shuffle();
    // }

    @Test
    public void testDealHand() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            cards.add(card);
        }

        when(deck.getCards()).thenReturn(cards);

        List<Card> hand = deckService.dealHand(deck);

        // Verify that the correct number of cards were dealt
        assertEquals(5, hand.size());
    }

    @Test
    public void testDeal() {
        List<Card> cards = new ArrayList<>();
        cards.add(card);

        when(deck.getCards()).thenReturn(cards);

        Card dealtCard = deckService.deal(deck);

        // Verify that the correct card was dealt
        assertEquals(card, dealtCard);
    }
}

