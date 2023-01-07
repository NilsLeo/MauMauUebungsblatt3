package de.htwberlin.kbe.gruppe4.inter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.htwberlin.kbe.gruppe4.impl.CLIServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CLIServiceImplTest {

    @Mock
    Card mockCard1;

    @Mock
    Card mockCard2;

    @Mock
    private PrintStream mockPrintStream;

    @Test
    public void testDisplayRules() {
        CLIServiceImpl cliServiceImpl = new CLIServiceImpl();
        cliServiceImpl.displayRules();

        verify(mockPrintStream).println("Welcome to MauMau!");
        verify(mockPrintStream).println("Here are the rules you can choose from:");
        verify(mockPrintStream).println("1. Draw two on seven: Whenever a player plays a seven, the next player has to draw two cards and forfeit their turn.");
        verify(mockPrintStream).println("2. Choose suit on jack: Whenever a player plays a jack, they get to choose the suit that the next player has to follow.");
        verify(mockPrintStream).println("3. Play again on ace: Whenever a player plays an ace, they get to play again.");
        verify(mockPrintStream).println("Enter 'y' to enable a rule or 'n' to disable it:");
    }

    @Test
    public void testDisplayHand() {
        CLIServiceImpl cliServiceImpl = new CLIServiceImpl();

        List<Card> hand = new ArrayList<>();
        when(mockCard1.toString()).thenReturn("ACE of SPADES");
        when(mockCard2.toString()).thenReturn("KING of HEARTS");
        hand.add(mockCard1);
        hand.add(mockCard2);

        String expectedOutput = "Player's hand:\n0: ACE of SPADES\n1: KING of HEARTS\n";
        StringBuilder outputBuilder = new StringBuilder();
        cliServiceImpl.displayHand("Player", hand);

        assertEquals(expectedOutput, outputBuilder.toString());
    }
}