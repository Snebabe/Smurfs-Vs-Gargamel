package com.group9.view;

import com.group9.controller.GameController;
import com.group9.model.Observer;
import com.group9.model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Observer {
    private static Model model;
    private static GameController controller;
    private InfoPanel infoPanel;

    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    public View(int WIDTH, int HEIGHT, Model model) {
        // Set up the JFrame
        this.model = model;
        this.controller = new GameController(model);

        this.setTitle("Smurfs vs. Gargamel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());

        // Initialize components
        gamePanel = new GamePanel(model, controller, WIDTH, HEIGHT);
        controlPanel = new ControlPanel(model, controller);
        infoPanel = new InfoPanel(model);


        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void update() {
        gamePanel.update();
        infoPanel.update();
    }



    /*public void setCount(int count) {
        countLabel.setText("Count: " + count);
    }

    public void addIncrementListener(ActionListener listener) {
        incrementButton.addActionListener(listener);
    }

    public void addDecrementListener(ActionListener listener) {
        decrementButton.addActionListener(listener);
    }*/
}
