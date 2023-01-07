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
        System.out.println("chosensuit " + rules.getSuit() + " cardsuit " + suit);
        if (rules.isChooseSuitOnJack()) {
            if (rules.getSuit() != null) {
                System.out.println("r1");
                valid = (rules.getSuit() == suit) ? (true) : (false);
                System.out.println("r1v " + valid);
                rules.setSuit(null);
            } else {
                System.out.println("r2");

                valid = (leadSuit == suit || leadRank == rank) ? (true) : (false);
            }
        } else {
            System.out.println("r3");

            valid = (leadSuit == suit || leadRank == rank) ? (true) : (false);
        }
        System.out.println("valid: " + valid);
        return valid;

    }
}
