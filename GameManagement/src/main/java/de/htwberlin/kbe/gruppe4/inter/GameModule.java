package de.htwberlin.kbe.gruppe4.inter;
import com.google.inject.AbstractModule;
import de.htwberlin.kbe.gruppe4.impl.*;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
      bind(CLIService.class).to(CLIServiceImpl.class);
      bind(DeckService.class).to(DeckServiceImpl.class);
      bind(RulesService.class).to(RulesServiceImpl.class);
      bind(PlayerService.class).to(PlayerServiceImpl.class);
    }
  }