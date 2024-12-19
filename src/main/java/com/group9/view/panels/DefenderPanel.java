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
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Set layout for buttons
        setOpaque(false);  // Make panel transparent

        //TODO: Could we remove these???
        int ImageButtonWidth = 50;
        int ImageButtonHeight = 50;

        for (DefenderType type : EntityConfiguration.getDefenderTypes()) {
            // Load the image
            Image defenderIcon = ImageLoader.loadResizedImage("/images/defenders/" + type.getName().toLowerCase() + "/idle/0.png", ImageButtonWidth, ImageButtonHeight);

            // Create a button that acts as the entire panel
            JButton button = new JButton();
            button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS)); // Arrange components vertically
            button.setFocusPainted(false); // Remove focus effect
            button.setOpaque(false); // Make button transparent
            button.setToolTipText("Health: " + type.getMaxHealth() + "; " +
                    "Damage: " + type.getAttackDamage() + "; " +
                    "Range: " + type.getRange() + "; " +
                    "Attack Speed: " + type.getAttackSpeed());  // Display defender stats on hover


            buttons.add(button);  // Add button to tracking list

            // Action listener for selecting a defender
            button.addActionListener(e -> {
                selectedButton = button; // Mark this button as selected
                for (InputObserver observer : observers) {
                    observer.onDefenderSelected(type); // Notify observers
                }
                repaint();
            });

            Font smallerLabelFont = button.getFont().deriveFont(Font.BOLD, 14); // Font for labels

            // Cost label (above the image)
            JLabel costLabel = new JLabel("Cost: " + type.getCost());
            costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            costLabel.setFont(smallerLabelFont);

            // Label displaying the defender's name below the image
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

    // This method is responsible for updating the button borders when redrawing the panel.
    // It visually indicates the selected button by applying a custom border to it, while
    // non-selected buttons are given a default border.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define the borders for selected buttons
        LineBorder yellowThickBorder = new LineBorder(Color.darkGray, 5, false);
        EmptyBorder paddingBorder = new EmptyBorder(0, 5, 0, 5);
        CompoundBorder selectedBorder = new CompoundBorder(yellowThickBorder, paddingBorder);

        // Loop through each button to update the border if selected
        for (JButton button : buttons) {
            if (button == selectedButton) {
                button.setBorder(selectedBorder); // Apply border to selected button
            } else {
                button.setBorder(new EmptyBorder(5, 10, 5, 10)); // Default border for non-selected buttons
            }
        }
    }

    public Map<JButton, DefenderType> getButtonMap() {
        return buttonMap;
    }

}
