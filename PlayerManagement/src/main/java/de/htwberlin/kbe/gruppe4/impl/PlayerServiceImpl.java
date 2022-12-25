package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.inter.*;

public class PlayerServiceImpl implements PlayerService {
    DeckServiceImpl deckService;

    @Override
    public void dealHand(Player player, Deck deck) {
        player.setHand(deckService.dealHand(deck));
    }

    @Override
    public void draw(Player player, Card card) {
        player.getHand().add(card);
    }

    @Override
    public Card play(Player player, int index, Card.Suit leadSuit, Card.Rank leadRank) {
        if (index < 0 || index >= player.getHand().size()) {
            return null;
        }
        Card card = player.getHand().get(index);
        if (card.getSuit() != leadSuit && card.getRank() != leadRank) {
            return null;
        }
        player.getHand().remove(index);
        return card;
    }

}