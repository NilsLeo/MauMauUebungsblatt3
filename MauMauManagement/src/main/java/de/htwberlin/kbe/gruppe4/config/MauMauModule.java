package de.htwberlin.kbe.gruppe4.config;

import com.google.inject.AbstractModule;

import de.htwberlin.kbe.gruppe4.impl.CLIServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.GameServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.RulesServiceImpl;
import de.htwberlin.kbe.gruppe4.inter.CLIService;
import de.htwberlin.kbe.gruppe4.inter.DeckService;
import de.htwberlin.kbe.gruppe4.inter.GameService;
import de.htwberlin.kbe.gruppe4.inter.PlayerService;
import de.htwberlin.kbe.gruppe4.inter.RulesService;

public class MauMauModule extends AbstractModule{
  @Override
  protected void configure() {
      bind(CLIService.class).to(CLIServiceImpl.class);
      bind(GameService.class).to(GameServiceImpl.class);
      bind(PlayerService.class).to(PlayerServiceImpl.class);
      bind(RulesService.class).to(RulesServiceImpl.class);
      bind(DeckService.class).to(DeckServiceImpl.class);
  }
}
