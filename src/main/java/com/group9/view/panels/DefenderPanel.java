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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The DefenderPanel class represents a panel that displays a selection of defender buttons.
 * Each button corresponds to a different type of defender, showing its image, cost, and name.
 * The panel allows users to select a defender, which is visually indicated by a border.
 */

public class DefenderPanel extends JPanel {

    private final Map<JButton, DefenderType> buttonMap = new HashMap<>();
    private JButton selectedButton = null;

    public DefenderPanel(List<InputObserver> observers) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Set layout for buttons
        setOpaque(false);  // Make panel transparent

        for (DefenderType type : EntityConfiguration.getDefenderTypes()) {

            // Create a button to display the defender
            JButton button = createDefenderButton(type, observers);

            // Style the button with the defender's image, cost, and name
            styleDefenderButton(button, type);

            // Add the button to the panel
            add(button);

        }
    }

    private JButton createDefenderButton (DefenderType type, List<InputObserver> observers) {
        int preferredButtonWidth = 100;
        int preferredButtonHeight = 95;
        JButton button = new JButton() {
            @Override
            public void updateUI() {
                super.updateUI();
                setModel(new DefaultButtonModel() {
                    @Override
                    public void setEnabled(boolean b) {
                        super.setEnabled(b);
                        if (!b) {
                            setBackground(Color.GRAY);
                        } else {
                            setBackground(Color.LIGHT_GRAY);
                        }
                    }
                });
            }
        };

        button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS)); // Arrange components vertically
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setToolTipText(type.getInfo());  // Display defender stats on hover
        button.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));

        // Add button to map in order to change border when selected
        buttonMap.put(button, type);

        button.addActionListener(e -> {
            selectedButton = button;
            for (InputObserver observer : observers) {
                observer.onDefenderSelected(type);
            }
            repaint();
        });

        return button;
    }

    private void styleDefenderButton(JButton button, DefenderType type) {
        int ImageButtonWidth = 50;
        int ImageButtonHeight = 50;

        Image defenderIcon = ImageLoader.loadResizedImage("/images/defenders/" + type.getName().toLowerCase() + "/idle/0.png", ImageButtonWidth, ImageButtonHeight);
        Font smallerLabelFont = button.getFont().deriveFont(Font.BOLD, 14); // Font for labels

        // Cost panel (above the image)
        JPanel costPanel = new JPanel();
        costPanel.setLayout(new BoxLayout(costPanel, BoxLayout.X_AXIS));
        costPanel.setOpaque(false);

        // Smurf coin image
        Image smurfCoinImage = ImageLoader.loadResizedImage("/images/smurfCoin.png", 20, 20);
        JLabel smurfCoinLabel = new JLabel(new ImageIcon(smurfCoinImage));
        smurfCoinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cost label
        JLabel costLabel = new JLabel(String.valueOf(type.getCost()));
        costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        costLabel.setFont(smallerLabelFont);

        // Add components to the cost panel
        costPanel.add(smurfCoinLabel);
        costPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between image and cost
        costPanel.add(costLabel);

        // Label displaying the defender's name below the image
        JLabel imageLabel = new JLabel(new ImageIcon(defenderIcon));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Defender type label (below the image)
        JLabel nameLabel = new JLabel(type.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(smallerLabelFont);

        // Add components to the button
        button.add(costPanel);
        button.add(imageLabel);
        button.add(nameLabel);
    }

    /**
     * This method is responsible for updating the button borders when redrawing the panel.
     * It visually indicates the selected button by applying a custom border to it, while
     * non-selected buttons are given a default border.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define the borders for selected buttons
        LineBorder darkGrayThickBorder = new LineBorder(Color.darkGray, 5, false);
        EmptyBorder paddingBorder = new EmptyBorder(0, 5, 0, 5);
        CompoundBorder selectedBorder = new CompoundBorder(darkGrayThickBorder, paddingBorder);

        // Loop through each button to update the border if selected
        for (JButton button : buttonMap.keySet()) {
            if (button == selectedButton) {
                button.setBorder(selectedBorder); // Apply darkGrey border to selected button
            } else {
                button.setBorder(new EmptyBorder(5, 10, 5, 10)); // Default padding border
            }
        }
    }

    public Map<JButton, DefenderType> getButtonMap() {
        return buttonMap;
    }

}