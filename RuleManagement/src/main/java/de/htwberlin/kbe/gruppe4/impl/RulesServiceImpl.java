package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Rules;
import de.htwberlin.kbe.gruppe4.inter.RulesService;
import javax.inject.Singleton;
@Singleton
public class RulesServiceImpl implements RulesService {

    @Override
    public boolean isCardValid(Card card, Card.Suit leadSuit, Card.Rank leadRank, Rules rules) {
        Card.Suit suit = card.getSuit();
        Card.Rank rank = card.getRank();
        boolean valid = false;
        if (suit == leadSuit || rank == leadRank && !rules.isChosenSuitTemporarilyEnabled()) {
            valid = true;
        }

        if (rules.isChooseSuitOnJack() && rules.isChosenSuitTemporarilyEnabled() && rules.getSuit() == suit) {
            valid = true;
        }

        return valid;
    }
}


