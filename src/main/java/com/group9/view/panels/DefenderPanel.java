package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.view.renderers.EntityRenderer;

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
        setBorder(BorderFactory.createTitledBorder("Defenders"));

        for (DefenderType type : DefenderType.values()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel costLabel = new JLabel("Cost: " + type.getCost());
            costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton button = new JButton(type.toString());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add button to tracking list
            buttons.add(button);

            // Add ActionListener to handle selection
            button.addActionListener(e -> {
                selectedButton = button; // Mark this button as selected
                for (InputObserver observer : observers) {
                    observer.onDefenderSelected(type);
                }
                repaint(); // Trigger repaint to update borders
            });

            panel.add(costLabel);
            panel.add(button);
            add(panel);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define borders
        LineBorder yellowThickBorder = new LineBorder(Color.YELLOW, 5, false);
        EmptyBorder paddingBorder = new EmptyBorder(5, 15, 5, 15);
        CompoundBorder selectedBorder = new CompoundBorder(yellowThickBorder, paddingBorder);

        for (JButton button : buttons) {
            if (button == selectedButton) {
                button.setBorder(selectedBorder); // Apply yellow border to selected button
            } else {
                button.setBorder(new EmptyBorder(10, 20, 10, 20)); // Default padding border
            }
        }
    }
}
