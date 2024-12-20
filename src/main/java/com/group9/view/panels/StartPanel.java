package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private final Image backgroundImage;

    /**
     * Constructs a StartPanel as an entrypoint to the game.
     *
     * @param startButtonListener the action listener for the start button
     * @param helpButtonListener the action listener for the help button
     */
    public StartPanel(ActionListener startButtonListener, ActionListener helpButtonListener) {
        this.backgroundImage = ImageLoader.loadImage("/images/backgrounds/startPanelBg.png");

        // Set the layout for the panel to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel to contain the buttons with FlowLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        // Button to start the game
        // Create and configure the start button
        JButton startButton = ImageButtonFactory.createImageButton("/images/buttons/playBtn.png", 200, 80);
        startButton.addActionListener(startButtonListener);
        buttonPanel.add(startButton);

        // Button to navigate to the help screen
        // Create and configure the help button
        JButton helpButton = ImageButtonFactory.createImageButton("/images/buttons/helpBtn.png", 200, 80);
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