package de.htwberlin.kbe.gruppe4.inter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;

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
    @Test
    public void testShuffle() {
        // create a mock of the Deck interface
        Deck mockDeck = Mockito.mock(Deck.class);
    
        // set up the mock to return a specific list of cards when the getCards method is called
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT));
        Mockito.when(mockDeck.getCards()).thenReturn(cards);
    
        // create an instance of the DeckServiceImpl class
        DeckServiceImpl deckService = new DeckServiceImpl();
    
        // create a copy of the original list of cards
        List<Card> originalCards = new ArrayList<>(cards);
    
        // call the shuffle method of the deckService instance
        deckService.shuffle(mockDeck);

        // verifies that the 2 lists aren't equal, since they have a different order
        assertNotEquals(mockDeck.getCards(), originalCards);
        // verifies that the 2 lists have the same elements regardless of order
        assertTrue(() -> originalCards.containsAll(mockDeck.getCards()) && mockDeck.getCards().containsAll(originalCards));
    }

    @Test
    public void testDealHandSize() {
        // create an instance of the DeckServiceImpl class
        DeckServiceImpl deckService = new DeckServiceImpl();
        
        List<Card> hand = deckService.dealHand(deck);
        
        assertEquals(5, hand.size());
    }


    @Test
    public void testDeal() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT));

        when(deck.getCards()).thenReturn(cards);

        Card card = cards.get(0);
        Card dealtCard = deckService.deal(deck);
        Card cardAfterDeal = cards.get(0);


        // Verify that the correct card was dealt
        assertEquals(card, dealtCard);
        assertNotEquals(cardAfterDeal, dealtCard);
    }
}

