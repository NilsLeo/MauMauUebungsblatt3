package de.htwberlin.kbe.gruppe4.inter;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;

public class PlayerServiceImplTest {
    @Mock
    private DeckService mockDeckService;
    private PlayerService playerService;
    private Deck deck;
    private Card card1;
    private Card card2;
    private Player player;

    @BeforeEach
    public void setup() {
        playerService = new PlayerServiceImpl(mockDeckService);
        deck = new Deck();
        card1 = new Card(Card.Suit.SPADES, Card.Rank.EIGHT);
        card2 = new Card(Card.Suit.CLUBS, Card.Rank.SEVEN);
        player = new Player("player1");
    }

    @Test
    public void testDraw() {
        playerService.draw(player, card1);
        assertEquals(1, player.getHand().size());
        assertEquals(card1, player.getHand().get(0));
    }

    @Test
    public void testPlay() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);
        hand.add(card1);
        player.setHand(hand);
        Card playedCard = playerService.play(player, 2, Card.Suit.SPADES, Card.Rank.EIGHT);
        assertEquals(card1, playedCard);
        assertEquals(2, player.getHand().size());
    }
}
       