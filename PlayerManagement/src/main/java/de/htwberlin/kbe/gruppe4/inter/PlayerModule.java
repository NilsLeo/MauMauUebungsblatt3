package de.htwberlin.kbe.gruppe4.inter;

import com.google.inject.AbstractModule;

import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;

public class PlayerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DeckService.class).to(DeckServiceImpl.class);
        bind(PlayerService.class).to(PlayerServiceImpl.class);
    }
}