package de.htwberlin.kbe.gruppe4.inter;

public interface RulesService {
    boolean isCardValid(Card card, Card.Suit leadSuit, Card.Rank leadRank, Rules rules);
}
