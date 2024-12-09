package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.entities.defenders.DefenderType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefenderPanel extends JPanel {
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
            button.addActionListener(e -> {
                for (InputObserver observer : observers) {
                    observer.onDefenderSelected(type);
                }
            });

            panel.add(costLabel);
            panel.add(button);
            add(panel);
        }
    }
}
