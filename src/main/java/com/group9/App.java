package com.group9;

import com.group9.controller.Controller;
import com.group9.model.GameManager;
import com.group9.model.Model;
import com.group9.view.View;

/**
 * Initierat med Maven. Broberg verkade rekommendera det
 * finns typ gradle och sånt också men detta ska vara lättare
 */
public class App {
    public static void main(String[] args) {

        // Initialize the MVC components
        Model model = new Model();
        View view = new View(640, 480);
        new Controller(model, view);

        GameManager gameManager = new GameManager(model);
        gameManager.start();

        // Make the View visible
        view.setVisible(true);
    }
}
