package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.WaveCompleteListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControlPanel extends JPanel implements WaveCompleteListener {
    private JButton startWaveButton;
    private JButton resetGameButton;
    private final List<InputObserver> inputObservers;
    private final Model model;

    public ControlPanel(Model model, List<InputObserver> inputObservers) {
        this.model = model;
        this.inputObservers = inputObservers;

        // Register this panel as a WaveCompleteListener
        this.model.getWaveManager().addWaveCompleteListener(this);

        // Set up the layout and visual appearance of the control panel
        setupLayout();

        // Initialize buttons and listeners
        initializeComponents();
        initializeListeners();

        // Initially disable the Start Wave button
        setStartWaveButtonEnabled(false);
    }

    /**
     * Sets up the layout and basic properties of the panel.
     */
    private void setupLayout() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Controls"));
    }

    /**
     * Initializes components for the control panel.
     */
    private void initializeComponents() {
        // Buttons
        startWaveButton = new JButton("Start Wave");
        resetGameButton = new JButton("Reset Game");

        // Add a panel for defender selection
        add(new DefenderPanel(model.getDefenderTypes(), inputObservers));

        // Add buttons to the control panel
        add(startWaveButton);
        add(resetGameButton);
    }

    /**
     * Initializes listeners for the buttons.
     */
    private void initializeListeners() {
        // Listener for the Start Wave button
        startWaveButton.addActionListener(e -> {
            setStartWaveButtonEnabled(false); // Disable the button after starting the wave
            for (InputObserver observer : inputObservers) {
                observer.onStartWaveClicked();
            }
        });

        // Listener for the Reset Game button
        resetGameButton.addActionListener(e -> {
            for (InputObserver observer : inputObservers) {
                observer.onResetGameClicked();
            }
        });
    }

    /**
     * Enables or disables the Start Wave button.
     */
    public void setStartWaveButtonEnabled(boolean enabled) {
        startWaveButton.setEnabled(enabled);
    }

    /**
     * Callback method invoked when a wave is completed.
     */
    @Override
    public void onWaveComplete(int waveReward) {
        setStartWaveButtonEnabled(true);
    }
}
