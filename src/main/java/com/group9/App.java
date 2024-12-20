package com.group9;

import com.group9.controller.GameController;
import com.group9.model.Clock;
import com.group9.model.Model;
import com.group9.view.View;

/**
 * The App class serves as the entry point for the application, initializing the MVC components
 * and starting the game clock.
 */
public class App {
    public static void main(String[] args) {

        int TICKS_PER_SECONDS = 120;

        Clock clock = new Clock(TICKS_PER_SECONDS);
        Model model = new Model(TICKS_PER_SECONDS,5,9);
        View view = new View(980, 560, model, clock);
        GameController controller = new GameController(model);

        view.addInputObserver(controller);

        clock.addObserver(model);
        clock.start();
    }
}
