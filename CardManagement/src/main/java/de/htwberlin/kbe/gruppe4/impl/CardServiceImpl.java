package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.CardService;

import java.util.ArrayList;
import java.util.List;



public class CardServiceImpl implements CardService {
    @Override
    public List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(suit, rank);
                deck.add(card);
            }
        }
        return deck;
    }
}