package de.htwberlin.kbe.gruppe4.inter;

public interface PlayerService {
    void dealHand(Player player, Deck deck);

    void draw(Player player, Card card);

    Card play(Player player, int index, Card.Suit leadSuit, Card.Rank leadRank);

    DeckService getDeckService();

    void setDeckService(DeckService deckService);
}