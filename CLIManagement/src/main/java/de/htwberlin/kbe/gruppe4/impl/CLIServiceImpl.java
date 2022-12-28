package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.inter.CLIService;
import de.htwberlin.kbe.gruppe4.inter.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.inject.Singleton;
@Singleton
public class CLIServiceImpl implements CLIService {
    private final Scanner scanner;

    public CLIServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayRules() {
        System.out.println("Welcome to MauMau!");
        System.out.println("Here are the rules you can choose from:");
        System.out.println("1. Draw two on seven: Whenever a player plays a seven, the next player has to draw two cards and forfeit their turn.");
        System.out.println("2. Choose suit on jack: Whenever a player plays a jack, they get to choose the suit that the next player has to follow.");
        System.out.println("3. Reverse on ace: Whenever a player plays an ace, the order of play is reversed.");
        System.out.println("Enter 'y' to enable a rule or 'n' to disable it:");
    }

    @Override
    public boolean getRule(String name) {
        System.out.print("Enable " + name + "? (y/n): ");
        String input = scanner.nextLine();
        return input.equals("y");
    }

    @Override
    public void displayHand(String name, List<Card> hand) {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }

    @Override
    public void displayLead(Card.Suit suit, Card.Rank rank) {
        System.out.println("Top card on the table: " + suit + " " + rank);
    }

    @Override
    public String getPlayOrDraw() {
        System.out.print("Enter the number of the card you want to play (or 'd' to draw a card): ");
        return scanner.nextLine();
    }

    @Override
    public void announceInvalid() {
        System.out.println("Invalid input. Try again.");
    }

    @Override
    public void announcePlay(String name, Card card) {
        System.out.println(name + " played " + card);
    }

    @Override
    public void announceWinner(String name) {
        System.out.println(name + " won the game!");
    }
    @Override
    public List<String> getPlayerNames() {
        System.out.print("Enter the number of players (2-5): ");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter the name of player " + i + ": ");
            names.add(scanner.nextLine());
        }
        return names;
    }
}