package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Rules;
import de.htwberlin.kbe.gruppe4.inter.RulesService;

public class RulesServiceImpl implements RulesService {

    @Override
    public boolean isCardValid(Card card, Card.Suit leadSuit, Card.Rank leadRank, Rules rules) {
        Card.Suit suit = card.getSuit();
        Card.Rank rank = card.getRank();
        boolean valid = false;
        // System.out.println("chosensuit " + rules.getSuit() + " cardsuit " + suit);
        if (rules.isChooseSuitOnJack()) {
            if (rules.getSuit() != null) {
                valid = (rules.getSuit() == suit) ? (true) : (false);
                rules.setSuit(null);
            } else {

                valid = (leadSuit == suit || leadRank == rank) ? (true) : (false);
            }
        } else {

            valid = (leadSuit == suit || leadRank == rank) ? (true) : (false);
        }
        return valid;

    }
}
