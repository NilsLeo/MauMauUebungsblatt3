package de.htwberlin.kbe.gruppe4.inter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.GameServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.RulesServiceImpl;

public class GameServiceImplTest {
    
    private DeckService deckService;
    
    private RulesService rulesService;
    
    private PlayerService playerService;

    private GameServiceImpl gameService;

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private List<String> playerNames;
    private List<Player> players;
    private Rules rules;
    List<Card> cards;
    Deck deck;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        deck = new Deck();
        deckService = new DeckServiceImpl();
        playerService = new PlayerServiceImpl(deckService);
        rulesService = new RulesServiceImpl();
        gameService = new GameServiceImpl(deckService, rulesService, playerService);

        playerNames = new ArrayList<>();
        playerNames.add("Player 1");
        playerNames.add("Player 2");
        playerNames.add("Player 3");
        playerNames.add("Player 4");

        players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
        players.add(new Player("Player 4"));

        card1 = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
        card2 = new Card(Card.Suit.CLUBS, Card.Rank.SEVEN);
        card3 = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);
        card4 = new Card(Card.Suit.CLUBS, Card.Rank.NINE);
        card5 = new Card(Card.Suit.CLUBS, Card.Rank.TEN);

        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        rules = new Rules();
    }

    @Test
    public void testSetPlayers() {
        gameService.setPlayers(playerNames);
        assertEquals(players.get(0).getName(), gameService.getPlayers().get(0).getName());
    }
    @Test
    public void testSetRules() {
        boolean drawTwoOnSeven = true;
        boolean chooseSuitOnJack = false;
        boolean reverseOnAce = true;
        
        gameService.setRules(drawTwoOnSeven, chooseSuitOnJack, reverseOnAce);
        Rules actualRules = gameService.getRules();

        assertEquals(drawTwoOnSeven, actualRules.isDrawTwoOnSeven());
        assertEquals(chooseSuitOnJack, actualRules.isChooseSuitOnJack());
        assertEquals(reverseOnAce, actualRules.isReverseOnAce());
    }
    
    @Test
    public void testStartGame() {
        deck.setCards(cards);
        deckService.shuffle(deck);
        gameService.setPlayers(playerNames);
        gameService.startGame();
        // checks that the deck has been shuffled
        // check if the card are dealt to players
        for(Player p : players){
        playerService.dealHand(p, deck);
        }
        // check if the table has a card
        assertTrue(gameService.getLeadCard() != null);
    }

    @Test
    public void testAddCardToTable() {
        gameService.addCardToTable(card1);
        // check if the card is added to the table
        assertEquals(card1, gameService.getLeadCard());
    }
    @Test
    public void testDrawCard() {
        Player player = players.get(0);
        deckService.deal(deck);
        Card drawnCard = gameService.drawCard(player);
        // check if the card is drawn from the deck
        assertEquals(card2, drawnCard);
    }
    @Test
    public void TestPlayCard() {
        Player player = players.get(0);
        gameService.addCardToTable(card1);
        card2 = playerService.play(player, 0, gameService.getLeadSuit(), gameService.getLeadRank());
        Card playedCard = gameService.playCard(player, 0);
        // check if the card is played
        assertEquals(card2, playedCard);
    }
    @Test
    public void testGetLeadSuit() {
        gameService.addCardToTable(card1);
        Card.Suit expectedSuit = card1.getSuit();
        // check if the lead card's suit is returned
        assertEquals(expectedSuit, gameService.getLeadSuit());
    }
    @Test
    public void testGetLeadRank() {
        gameService.addCardToTable(card1);
        Card.Rank expectedRank = card1.getRank();
        // check if the lead card's rank is returned
        assertEquals(expectedRank, gameService.getLeadRank());
    }



}