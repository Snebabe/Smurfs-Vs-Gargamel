package com.group9.view.panels;

import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The HelpPanel class represents a panel that displays the help instructions for the game.
 * It includes a back button to return to the previous screen, and a text area
 * with game instructions.
 */
public class HelpPanel extends JPanel {
    private final Image backgroundImage;

    /**
     * Constructs a HelpPanel to display instructions.
     *
     * @param backButtonListener the action listener for the back button
     */
    public HelpPanel(ActionListener backButtonListener) {
        backgroundImage = ImageLoader.loadImage("/images/backgrounds/controlPanelBg.jpg");

        JButton backButton = ImageButtonFactory.createImageButton("/images/buttons/backBtn.png", 200, 80);
        backButton.addActionListener(backButtonListener);

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

        instructions.setEditable(false);
        instructions.setForeground(Color.WHITE);
        instructions.setLineWrap(true);
        instructions.setOpaque(false);
        instructions.setWrapStyleWord(true);

        // Place the instructions in a scrollable area
        JScrollPane scrollPane = new JScrollPane(instructions);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for the back button at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
