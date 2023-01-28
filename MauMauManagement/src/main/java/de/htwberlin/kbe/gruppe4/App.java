package de.htwberlin.kbe.gruppe4;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwberlin.kbe.gruppe4.config.MauMauModule;
import de.htwberlin.kbe.gruppe4.inter.CLIController;

public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MauMauModule());
        CLIController controller = injector.getInstance(CLIController.class);
        controller.startGame();
    }
    
}
