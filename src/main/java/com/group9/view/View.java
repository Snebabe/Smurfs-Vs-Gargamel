package com.group9.view;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.view.panels.ControlPanel;
import com.group9.view.panels.GamePanel;
import com.group9.view.panels.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame implements Observer {
    private InfoPanel infoPanel;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;

    private final List<InputObserver> inputObservers = new ArrayList<>();

    public View(int WIDTH, int HEIGHT, Model model) {
        // Set up the JFrame

        this.setTitle("Smurfs vs. Gargamel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());

        // Initialize components
        gamePanel = new GamePanel(model, inputObservers);
        controlPanel = new ControlPanel(model, inputObservers);
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

    public void addInputObserver(InputObserver observer) {
        inputObservers.add(observer);
    }
}
