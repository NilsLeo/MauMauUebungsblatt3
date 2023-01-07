package de.htwberlin.kbe.gruppe4.inter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;
import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Deck;
import de.htwberlin.kbe.gruppe4.inter.DeckService;
import de.htwberlin.kbe.gruppe4.inter.Player;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {
    private PlayerServiceImpl playerService;

    @Mock
    private Player player;

    @Mock
    private Deck deck;

    @Mock
    private Card card;

    @Mock
    private DeckService deckService;

    @BeforeEach
    public void setUp() {
        playerService = new PlayerServiceImpl();
        playerService.setDeckService(deckService);
    }

    @Test
    public void testGetDeckService() {
        assertEquals(deckService, playerService.getDeckService());
    }

    @Test
    public void testDealHand() {
        playerService.dealHand(player, deck);
        verify(deckService).dealHand(deck);
        verify(player).setHand(deckService.dealHand(deck));
    }

    @Test
    public void testDraw() {
        playerService.draw(player, card);
        verify(player).getHand();
        verify(player).getHand().add(card);
    }

    @Test
    public void testPlay() {
        when(player.getHand()).thenReturn(List.of(card));
        assertEquals(card, playerService.play(player, 0, Card.Suit.CLUBS, Card.Rank.ACE));
        verify(player).getHand();
        verify(player).getHand().remove(0);
    }
}