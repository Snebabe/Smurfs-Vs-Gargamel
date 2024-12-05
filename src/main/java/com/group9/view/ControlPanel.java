package com.group9.view;

import com.group9.controller.Controller;
import com.group9.controller.GameController;
import com.group9.model.DefenderType;
import com.group9.model.Model;
import com.group9.model.WaveCompleteListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JPanel implements WaveCompleteListener {
    private JButton addShroomButton;
    private JButton startWaveButton;
    private JButton startGameButton;
    private JButton resetGameButton;

    private Model model;
    private GameController controller;

    public ControlPanel(Model model, GameController controller) {
        this.controller = controller;
        this.model = model;
        this.model.getWaveManager().addWaveCompleteListener(this);

        // Set up the layout for the control panel
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Controls"));

        // Initialize the buttons
        addShroomButton = new JButton("Add Shroom");
        startWaveButton = new JButton("Start Wave");
        //startGameButton = new JButton("Start Game");
        resetGameButton = new JButton("Reset Game");

        // Add action listeners to the buttons
        addShroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleDefenderClick(DefenderType.SHROOM);
            }
        });

        startWaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Spawning Wave");
                model.startWave();
                setStartWaveButtonEnabled(false); // Disable the button after starting the wave
            }
        });

        /*
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start game logic here (you can customize this part)
                System.out.println("Starting game...");
            }
        });


         */
        resetGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Resetting game...");
                model.resetGame();
            }
        });

        // Add the buttons to the control panel
        add(addShroomButton);
        add(startWaveButton);
        //add(startGameButton);
        add(resetGameButton);

        // Initially disable the start wave button
        setStartWaveButtonEnabled(false);
    }
    public void setStartWaveButtonEnabled(boolean enabled) {
        startWaveButton.setEnabled(enabled);
    }

    @Override
    public void onWaveComplete() {
        setStartWaveButtonEnabled(true);
    }
}