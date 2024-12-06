package com.group9;

import com.group9.controller.GameController;
import com.group9.model.GameManager;
import com.group9.model.Model;
import com.group9.view.View;

public class App {
    public static void main(String[] args) {

        // Initialize the MVC components
        Model model = new Model();
        View view = new View(800, 480, model);
        GameController controller = new GameController(model);

        view.addInputObserver(controller);

        GameManager gameManager = new GameManager(model, view);
        gameManager.start();
    }
}
