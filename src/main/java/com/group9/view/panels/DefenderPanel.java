package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.entities.EntityConfiguration;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DefenderPanel extends JPanel {

    private final List<JButton> buttons = new ArrayList<>(); // To track all buttons
    private JButton selectedButton = null; // To track the selected button

    public DefenderPanel(List<InputObserver> observers) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setOpaque(false);

        int ImageButtonWidth = 50;
        int ImageButtonHeight = 50;

        for (DefenderType type : EntityConfiguration.getDefenderTypes()) {
            // Load the image
            Image defenderIcon = ImageLoader.loadResizedImage("/images/defenders/" + type.getName().toLowerCase() + "/idle/0.png", ImageButtonWidth, ImageButtonHeight);

            // Create a button that acts as the entire panel
            JButton button = new JButton();
            button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setToolTipText("Health: " + type.getMaxHealth() + "; " +
                    "Damage: " + type.getAttackDamage() + "; " +
                    "Range: " + type.getRange() + "; " +
                    "Attack Speed: " + type.getAttackSpeed());

            // Add button to tracking list
            buttons.add(button);
            button.addActionListener(e -> {
                selectedButton = button; // Mark this button as selected
                for (InputObserver observer : observers) {
                    observer.onDefenderSelected(type);
                }
                repaint();
            });

            Font smallerLabelFont = button.getFont().deriveFont(Font.BOLD, 14);
            // Cost label (above the image)
            JLabel costLabel = new JLabel("Cost: " + type.getCost());
            costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            costLabel.setFont(smallerLabelFont);

            // Image icon
            JLabel imageLabel = new JLabel(new ImageIcon(defenderIcon));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Defender type label (below the image)
            JLabel nameLabel = new JLabel(type.getName());
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setFont(smallerLabelFont);

            // Add components to the button
            button.add(costLabel);
            button.add(imageLabel);
            button.add(nameLabel);

            // Add the button to the panel
            add(button);

        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define borders
        LineBorder yellowThickBorder = new LineBorder(Color.darkGray, 5, false);
        EmptyBorder paddingBorder = new EmptyBorder(0, 5, 0, 5);
        CompoundBorder selectedBorder = new CompoundBorder(yellowThickBorder, paddingBorder);

        for (JButton button : buttons) {
            if (button == selectedButton) {
                button.setBorder(selectedBorder); // Apply yellow border to selected button
            } else {
                button.setBorder(new EmptyBorder(5, 10, 5, 10)); // Default padding border
            }
        }
    }




}
