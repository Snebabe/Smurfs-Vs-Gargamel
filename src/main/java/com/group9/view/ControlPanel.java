package com.group9.view;

import com.group9.controller.GameController;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.Model;
import com.group9.model.WaveCompleteListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JPanel implements WaveCompleteListener {
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
        startWaveButton = new JButton("Start Wave");
        //startGameButton = new JButton("Start Game");
        resetGameButton = new JButton("Reset Game");

        // Add action listeners to the buttons

        // Add buttons for each DefenderType
        for (DefenderType type : model.getDefenderTypes()) {
            // Create a panel to hold the label and button
            JPanel defenderPanel = new JPanel();
            defenderPanel.setLayout(new BoxLayout(defenderPanel, BoxLayout.Y_AXIS)); // Vertical layout

            // Create the cost label
            JLabel costLabel = new JLabel("Cost: " + type.getCost());
            costLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label

            // Create the button
            JButton button = new JButton(type.getName());
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.handleDefenderClick(type); // Pass the specific DefenderType to the controller
                }
            });

            // Add the label and button to the panel
            defenderPanel.add(costLabel);
            defenderPanel.add(button);

            // Add the panel to the main container
            add(defenderPanel);
        }

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
    public void onWaveComplete(int waveReward) {
        setStartWaveButtonEnabled(true);
    }
}