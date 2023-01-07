package de.htwberlin.kbe.gruppe4.inter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.htwberlin.kbe.gruppe4.impl.RulesServiceImpl;

public class RulesServiceImplTest {

    @Mock
    private Card card;

    @Mock
    private Rules rules;

    private RulesService rulesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        rulesService = new RulesServiceImpl();
    }

    @Test
    public void testIsCardValid_TemporarySuitDisabled_LeadSuit_ShouldReturnTrue() {
        when(card.getSuit()).thenReturn(Card.Suit.SPADES);
        when(rules.isChosenSuitTemporarilyEnabled()).thenReturn(false);
        when(rules.getSuit()).thenReturn(Card.Suit.HEARTS);

        boolean result = rulesService.isCardValid(card, Card.Suit.SPADES, Card.Rank.ACE, rules);

        assertTrue(result);
    }

    @Test
    public void testIsCardValid_TemporarySuitDisabled_LeadRank_ShouldReturnTrue() {
        when(card.getRank()).thenReturn(Card.Rank.ACE);
        when(rules.isChosenSuitTemporarilyEnabled()).thenReturn(false);
        when(rules.getSuit()).thenReturn(Card.Suit.HEARTS);

        boolean result = rulesService.isCardValid(card, Card.Suit.SPADES, Card.Rank.ACE, rules);

        assertTrue(result);
    }

    @Test
    public void testIsCardValid_TemporarySuitDisabled_DifferentSuit_ShouldReturnFalse() {
        when(card.getSuit()).thenReturn(Card.Suit.HEARTS);
        when(rules.isChosenSuitTemporarilyEnabled()).thenReturn(false);
        when(rules.getSuit()).thenReturn(Card.Suit.HEARTS);

        boolean result = rulesService.isCardValid(card, Card.Suit.SPADES, Card.Rank.ACE, rules);

        assertFalse(result);
    }

    @Test
    public void testIsCardValid_TemporarySuitEnabled_MatchingSuit_ShouldReturnTrue() {
        when(card.getSuit()).thenReturn(Card.Suit.HEARTS);
        when(rules.isChosenSuitTemporarilyEnabled()).thenReturn(true);
        when(rules.getSuit()).thenReturn(Card.Suit.HEARTS);

        boolean result = rulesService.isCardValid(card, Card.Suit.SPADES, Card.Rank.ACE, rules);

        assertTrue(result);
    }

}