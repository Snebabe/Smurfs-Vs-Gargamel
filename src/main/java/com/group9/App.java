package com.group9;

import com.group9.controller.GameController;
import com.group9.model.Clock;
import com.group9.model.Model;
import com.group9.view.View;

public class App {
    public static void main(String[] args) {

        // Initialize the MVC components
        int TICKS_PER_SECONDS = 120;
        Clock clock = new Clock(TICKS_PER_SECONDS);

        Model model = new Model(TICKS_PER_SECONDS);
        View view = new View(800, 480, model, clock);
        GameController controller = new GameController(model);

        view.addInputObserver(controller);

        clock.addObserver(model,0);
        // Attacks are updated every second
        clock.addObserver(model.getAttackManager(),1f);
        clock.start();
    }
}
