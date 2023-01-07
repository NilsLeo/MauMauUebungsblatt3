package de.htwberlin.kbe.gruppe4.inter;

import com.google.inject.AbstractModule;

import de.htwberlin.kbe.gruppe4.impl.CLIServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.GameServiceImpl;

public class MauMauModule extends AbstractModule{
    @Override
    protected void configure() {
      bind(GameService.class).to(GameServiceImpl.class);
      bind(CLIService.class).to(CLIServiceImpl.class);
    }
}
