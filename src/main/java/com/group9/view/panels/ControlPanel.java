package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.WaveCompleteListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ControlPanel extends JPanel implements WaveCompleteListener {
    private JButton startWaveButton;
    private JButton resetGameButton;
    private final List<InputObserver> inputObservers;
    private final Model model;

    private Image backgroundImage;


    public ControlPanel(Model model, List<InputObserver> inputObservers, String backgroundImagePath) {
        this.model = model;
        this.inputObservers = inputObservers;
        System.out.println(backgroundImagePath);

        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource(backgroundImagePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.err.println("Failed to load background image.");
        }

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


    //Paints the background image on the panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Sets up the layout and basic properties of the panel.
     */
    private void setupLayout() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    /**
     * Initializes components for the control panel.
     */
    private void initializeComponents() {
        // Buttons
        startWaveButton = new JButton("Start Wave");
        resetGameButton = new JButton("Reset Game");

        // Add a panel for defender selection
        add(new DefenderPanel(inputObservers));

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
