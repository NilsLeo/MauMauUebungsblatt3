package de.htwberlin.kbe.gruppe4.config;

import com.google.inject.AbstractModule;

import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.inter.DeckService;

public class PlayerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DeckService.class).to(DeckServiceImpl.class);
    }
}