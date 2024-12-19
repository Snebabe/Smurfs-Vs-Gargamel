package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private JButton startButton; // Button to start the game
    private JButton helpButton;  // Button to navigate to the help screen
    private Image backgroundImage; // Background image for the start panel

    public StartPanel(ActionListener startButtonListener, ActionListener helpButtonListener) {

        // Load the background image for the start panel
        this.backgroundImage = ImageLoader.loadImage("/images/backgrounds/startPanelBg.png");

        // Set the layout for the panel to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel to contain the buttons with FlowLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        // Create and configure the start button
        startButton = ImageButtonFactory.createImageButton("/images/buttons/playBtn.png", 200, 80);
        startButton.addActionListener(startButtonListener);
        buttonPanel.add(startButton);

        // Create and configure the help button
        helpButton = ImageButtonFactory.createImageButton("/images/buttons/helpBtn.png", 200, 80);
        helpButton.addActionListener(helpButtonListener);
        buttonPanel.add(helpButton);

        // Add the button panel to the grid at row 1
        gbc.gridy = 1;
        add(buttonPanel, gbc);
    }

    // Override paintComponent to render the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image (if available)
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}