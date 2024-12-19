package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel {
    private final Image backgroundImage; // Background image for the help panel

    public HelpPanel(ActionListener backButtonListener) {

        // Load the background image for the panel
        backgroundImage = ImageLoader.loadImage("/images/backgrounds/controlPanelBg.jpg");

        // Button to go back to the previous screen
        JButton backButton = ImageButtonFactory.createImageButton("/images/buttons/backBtn.png", 200, 80);
        backButton.addActionListener(backButtonListener);

        // Layout for the main panel
        setLayout(new BorderLayout());

        // Instructions text area
        JTextArea instructions = new JTextArea();
        instructions.setText("""
                Welcome to Smurfs vs. Gargamel!
                
                Objective:
                The goal of the game is to protect the Smurf village from Gargamels minions.
                You can place defenders on the board to prevent Gargamels minions from reaching the village.
                You can see your resources in the bottom left corner and how much each of the defenders cost.
                You can also see the current wave and how many attackers are left in the wave.
                
                Controls:
                To place a defender, click on a defender and then on an empty cell on the board.
                While hovering over the defenders you can see their stats.
                
                Tips:
                Place defenders wisely to optimize resources.
                
                Good luck!
                """);

        instructions.setEditable(false); // Prevent editing the instructions
        instructions.setForeground(Color.WHITE); // Set text color to white
        instructions.setLineWrap(true); // Enable text wrapping
        instructions.setOpaque(false); // Make the text area transparent
        instructions.setWrapStyleWord(true);  // Ensure text wraps properly at word boundaries

        // Place the instructions in a scrollable area
        JScrollPane scrollPane = new JScrollPane(instructions);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        scrollPane.setBorder(null); // Remove borders for cleaner appearance
        add(scrollPane, BorderLayout.CENTER);  // Add the scroll pane to the center of the panel

        // Panel for the back button at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setOpaque(false); // Make button panel transparent
        add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the south region of the layout
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
