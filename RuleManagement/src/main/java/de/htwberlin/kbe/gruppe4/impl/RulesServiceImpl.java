package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Rules;
import de.htwberlin.kbe.gruppe4.inter.RulesService;

public class RulesServiceImpl implements RulesService {
    @Override
    public boolean isCardValid(Card card, Card.Suit leadSuit, Card.Rank leadRank, Rules rules) {
        Card.Suit suit = card.getSuit();
        Card.Rank rank = card.getRank();
        if (suit == leadSuit || rank == leadRank) {
            return true;
        }
        if (rules.isDrawTwoOnSeven() && rank == Card.Rank.SEVEN) {
            return true;
        }
        if (rules.isChooseSuitOnJack() && rank == Card.Rank.JACK) {
            return true;
        }
        if (rules.isReverseOnAce() && rank == Card.Rank.ACE) {
            return true;
        }
        return false;
    }
}


