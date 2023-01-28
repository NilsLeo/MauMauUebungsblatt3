package de.htwberlin.kbe.gruppe4.inter;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Deck deck;
    private List<Card> table;
    private final Rules rules;
    private int currentPlayer;
    
    public Game() {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.rules = new Rules();
        this.table = new ArrayList<>();
        this.currentPlayer = 0;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public Deck getDeck() {
        return deck;
    }
    
    public List<Card> getTable() {
        return table;
    }
    
    public Rules getRules() {
        return rules;
    }
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
