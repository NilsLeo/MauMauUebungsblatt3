package de.htwberlin.kbe.gruppe4.inter;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.htwberlin.kbe.gruppe4.impl.RulesServiceImpl;
import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Rules;

@ExtendWith(MockitoExtension.class)
public class RulesServiceImplTest {
 
    @Mock
    private Card card;
 
    @Mock
    private Rules rules;
 
    private RulesServiceImpl rulesService;
    private Card.Suit leadSuit;
    private Card.Rank leadRank;

    @BeforeEach
    public void setUp() {
        rulesService = new RulesServiceImpl();
        leadSuit = Card.Suit.CLUBS;
        leadRank = Card.Rank.ACE;
    }
 
    @Test
    public void testIsCardValid_ShouldReturnTrue() {
        when(rules.isChooseSuitOnJack()).thenReturn(false);
        when(card.getSuit()).thenReturn(leadSuit);
        when(card.getRank()).thenReturn(leadRank);
        assertTrue(rulesService.isCardValid(card, leadSuit, leadRank, rules));
    }
    
    @Test
    public void testIsCardValid_ShouldReturnFalse() {
        when(rules.isChooseSuitOnJack()).thenReturn(false);
        when(card.getSuit()).thenReturn(Card.Suit.DIAMONDS);
        when(card.getRank()).thenReturn(Card.Rank.JACK);
        assertFalse(rulesService.isCardValid(card, leadSuit, leadRank, rules));
    }
   
    @Test
    public void testIsCardValid_ChooseSuitOnJack_ShouldReturnTrue() {
        when(rules.isChooseSuitOnJack()).thenReturn(true);
        when(rules.getSuit()).thenReturn(Card.Suit.CLUBS);
        when(card.getSuit()).thenReturn(Card.Suit.CLUBS);
        when(card.getRank()).thenReturn(Card.Rank.JACK);
        assertTrue(rulesService.isCardValid(card, leadSuit, leadRank, rules));
    }
 
}
