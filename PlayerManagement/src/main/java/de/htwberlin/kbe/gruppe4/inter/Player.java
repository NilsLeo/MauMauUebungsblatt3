package de.htwberlin.kbe.gruppe4.inter;

import java.util.ArrayList;


public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
                // for (Card card : hand) {
        //     if(card.getRank().equals(Card.Rank.SEVEN)){
        //         handOrderedByRank.add(card);
        //     }
        // } 
        
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}