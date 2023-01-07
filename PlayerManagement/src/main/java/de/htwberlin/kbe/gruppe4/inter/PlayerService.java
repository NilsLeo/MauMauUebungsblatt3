package de.htwberlin.kbe.gruppe4.inter;

import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;

public interface PlayerService {
    void dealHand(Player player, Deck deck);

    void draw(Player player, Card card);

    Card play(Player player, int index, Card.Suit leadSuit, Card.Rank leadRank);
}