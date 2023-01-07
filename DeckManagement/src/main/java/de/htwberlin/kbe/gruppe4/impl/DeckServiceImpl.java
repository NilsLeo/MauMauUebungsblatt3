package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Deck;
import de.htwberlin.kbe.gruppe4.inter.DeckService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeckServiceImpl implements DeckService {

    

    @Override
    public void shuffle(Deck deck) {
        List<Card> cards = deck.getCards();
        Collections.shuffle(cards);
        deck.setCards(cards);
    }

    @Override
    public ArrayList<Card> dealHand(Deck deck) {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(deal(deck));
        }
        return hand;
    }

    @Override
    public Card deal(Deck deck) {
        List<Card> cards = deck.getCards();
        if (cards.isEmpty()) {
            return null;
        }
        Card card = cards.get(0);
        cards.remove(0);
        deck.setCards(cards);
        return card;
    }
}