package com.group9;

import com.group9.controller.Controller;
import com.group9.controller.GameController;
import com.group9.controller.iController;
import com.group9.model.GameManager;
import com.group9.model.Model;
import com.group9.view.MainView;
import com.group9.view.View;
import com.group9.view.iView;

public class App {
    public static void main(String[] args) {

        // Initialize the MVC components
        Model model = new Model();
        MainView view = new MainView(800, 480, model);

        GameManager gameManager = new GameManager(model, view);
        gameManager.start();
    }
}
