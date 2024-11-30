package com.group9.view;

import com.group9.controller.Controller;
import com.group9.controller.GameController;
import com.group9.model.DefenderType;
import com.group9.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JButton addShroomButton;
    private JButton addZombieButton;
    private JButton startGameButton;
    private JButton resetGameButton;

    private static Model model;
    private static GameController controller;

    public ControlPanel(Model model, GameController controller) {
        this.controller = controller;
        this.model = model;
        // Set up the layout for the control panel
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Controls"));

        // Initialize the buttons
        addShroomButton = new JButton("Add Shroom");
        addZombieButton = new JButton("Add Zombie");
        startGameButton = new JButton("Start Game");
        resetGameButton = new JButton("Reset Game");

        // Add action listeners to the buttons
        addShroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleDefenderClick(DefenderType.SHROOM);
            }
        });

        addZombieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.spawnAttackerRandomly();
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start game logic here (you can customize this part)
                System.out.println("Starting game...");
            }
        });

        resetGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset game logic here (you can customize this part)
                System.out.println("Resetting game...");
            }
        });

        // Add the buttons to the control panel
        add(addShroomButton);
        add(addZombieButton);
        add(startGameButton);
        add(resetGameButton);
    }
}