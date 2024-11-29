package com.group9.view;

import com.group9.controller.GameController;
import com.group9.controller.Observer;
import com.group9.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView implements iView, Observer {

    private JPanel[][] cells;
    private GameController controller;
    private Model model;

    public MainView(int WIDTH, int HEIGHT, Model model, GameController controller) {
        this.model = model;
        this.controller = controller;

        JFrame frame = new JFrame("Smurfs VS Gargamel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel parentView = new JPanel(new BorderLayout());

        JPanel gameGrid = createGameGrid();
        JPanel controlPanel = createControlPanel();

        parentView.add(gameGrid, BorderLayout.CENTER);
        parentView.add(controlPanel, BorderLayout.SOUTH);

        frame.add(parentView, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createGameGrid() {
        // Hämtas från model eller nåt
        int rows = 5, cols = 9;
        JPanel gridPanel = new JPanel(new GridLayout(rows, cols));
        gridPanel.setBorder(BorderFactory.createTitledBorder("Game Grid"));

        cells = new JPanel[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(50, 50));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                // add mouse listener
                // should be MVC OK
                // as long interaction is delegated to controller

                int currentRow = row;
                int currentCol = col;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.handleCellClick(currentRow, currentCol);
                    }
                });

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }

        return gridPanel;
    }

    private void addEntityToCell(int row, int col, String imagePath) {
        JLabel entityLabel = new JLabel("Plant");
        cells[row][col].add(entityLabel); // Add entity to the specific cell
        cells[row][col].revalidate();    // Re-layout the cell
        cells[row][col].repaint();
    }

    private static JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        JButton addSmurfButton = new JButton("Add Smurf");
        JButton addZombieButton = new JButton("Add Zombie");
        JButton startGameButton = new JButton("Start Game");
        JButton resetGameButton = new JButton("Reset Game");

        controlPanel.add(addSmurfButton);
        controlPanel.add(addZombieButton);
        controlPanel.add(startGameButton);
        controlPanel.add(resetGameButton);

        return controlPanel;
    }
    @Override
    public void update() {
        this.addEntityToCell(5, 4, "lit");
    }
}
