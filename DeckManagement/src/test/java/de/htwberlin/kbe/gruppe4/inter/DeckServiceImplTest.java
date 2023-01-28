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

    private DeckServiceImpl deckService;

    @BeforeEach
    public void setUp() {
        deckService = new DeckServiceImpl();
    }

    @Test
    public void testShuffle() {
        // create an instance of the Deck class
        Deck deck = new Deck();
    
        // create a copy of the original list of cards
        List<Card> originalCards = new ArrayList<>(deck.getCards());
    
        // create an instance of the DeckServiceImpl class
        DeckServiceImpl deckService = new DeckServiceImpl();
    
        // call the shuffle method of the deckService instance
        deckService.shuffle(deck);

        // verifies that the 2 lists aren't equal, since they have a different order
        assertNotEquals(deck.getCards(), originalCards);
        // verifies that the 2 lists have the same elements regardless of order
        assertTrue(() -> originalCards.containsAll(deck.getCards()) && deck.getCards().containsAll(originalCards));
    }

    @Test
    public void testDealHandSize() {
    // create an instance of the Deck class
    Deck deck = new Deck();
        // create an instance of the DeckServiceImpl class
        DeckServiceImpl deckService = new DeckServiceImpl();
    
        // call the dealHand method of the deckService instance
        List<Card> hand = deckService.dealHand(deck);
        
        // check that the size of the hand is equal to 5
        assertEquals(5, hand.size());
    }
    

    @Test
    public void testDeal() {
        // create an instance of the Deck class
        Deck deck = new Deck();
        
        // set the cards of the deck
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEARTS, Card.Rank.SEVEN));
        cards.add(new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT));
        deck.setCards(cards);

        // create an instance of the DeckServiceImpl class
        DeckServiceImpl deckService = new DeckServiceImpl();

        // get the first card from the deck
        Card card = cards.get(0);

        // deal the first card
        Card dealtCard = deckService.deal(deck);

        // get the first card after deal
        Card cardAfterDeal = cards.get(0);

        // Verify that the correct card was dealt
        assertEquals(card, dealtCard);
        assertNotEquals(cardAfterDeal, dealtCard);
    }



}

