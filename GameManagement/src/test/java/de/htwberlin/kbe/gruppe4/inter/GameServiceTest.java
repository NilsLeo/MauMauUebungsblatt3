package de.htwberlin.kbe.gruppe4.inter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.htwberlin.kbe.gruppe4.impl.CLIServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.GameService;
import de.htwberlin.kbe.gruppe4.inter.Card;
import de.htwberlin.kbe.gruppe4.inter.Deck;
import de.htwberlin.kbe.gruppe4.inter.DeckService;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

@Test
public void testPlay_shouldSetGameRules() {
    // mock the CLIServiceImpl and set it to return specific values when the getRule method is called
    CLIServiceImpl cli = mock(CLIServiceImpl.class);
    when(cli.getRule("draw two on seven")).thenReturn(true);
    when(cli.getRule("choose suit on jack")).thenReturn(true);
    when(cli.getRule("reverse on ace")).thenReturn(true);

    // mock the other dependencies of the GameService class
    DeckService deckService = mock(DeckService.class);
    RulesService rulesService = mock(RulesService.class);
    PlayerService playerService = mock(PlayerService.class);

    // create a List of names to pass to the GameService constructor
    List<String> names = new ArrayList<>();
    names.add("player 1");
    names.add("player 2");
    names.add("player 3");

    // create an instance of the GameService class using the mock dependencies and the list of names
    GameService gameService = new GameService(names, cli, deckService, rulesService, playerService);

    // call the play method on the gameService instance
    gameService.play();

    // verify that the rules were set correctly by calling the getRule method on the Rules object 
    // that was passed to the GameService constructor
    verify(cli).getRule("draw two on seven");
    verify(cli).getRule("choose suit on jack");
    verify(cli).getRule("reverse on ace");
}

@Test
public void testPlay_shouldDealCardsToPlayers() {
    // mock the CLIServiceImpl
    CLIServiceImpl cli = mock(CLIServiceImpl.class);

    // mock the other dependencies of the GameService class
    DeckService deckService = mock(DeckService.class);
    RulesService rulesService = mock(RulesService.class);
    PlayerService playerService = mock(PlayerService.class);

    // create a List of names to pass to the GameService constructor
    List<String> names = new ArrayList<>();
    names.add("player 1");
    names.add("player 2");
    names.add("player 3");

    // create an instance of the GameService class using the mock dependencies and the list of names
    GameService gameService = new GameService(names, cli, deckService, rulesService, playerService);

    // call the play method on the gameService instance
    gameService.play();
}
}
