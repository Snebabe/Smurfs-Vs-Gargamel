package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.observers.WaveCompleteObserver;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;


/**
 * The ControlPanel class represents the main control interface for the game.
 * It includes buttons for starting waves and resetting the game.
 * The panel displays the visual aspects of the controls, and current wave and resource information
 */
public class ControlPanel extends JPanel implements WaveCompleteObserver {
    private JButton startWaveButton;
    private JButton resetGameButton;
    private JLabel resourcesLabel;
    private DefenderPanel defenderPanel;
    private JLabel waveLabel;
    private JLabel attackersLeftLabel;
    private JPanel wavePanel;
    private JPanel resourcePanel;
    private final List<InputObserver> inputObservers;
    private final Model model;
    private final Image backgroundImage;


    /**
     * Constructs a ControlPanel with the specified game model, input observers, and background image path.
     *
     * @param model the game model
     * @param inputObservers the list of input observers
     * @param backgroundImagePath the path to the background image
     */
    public ControlPanel(Model model, List<InputObserver> inputObservers, String backgroundImagePath) {
        this.model = model;
        this.inputObservers = inputObservers;
        setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f)); // Default greenish background
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Set panel layout ot FlowLayout

        backgroundImage = ImageLoader.loadImage(backgroundImagePath);

        // Register this panel as a WaveCompleteListener
        model.getWaveManager().addWaveCompleteObserver(this);

        initializeComponents();
        initializeListeners();

        // Initially disable the Start Wave button
        startWaveButton.setEnabled(false);
    }

    public void updateControlPanelState() {
        waveLabel.setText("Wave: " + model.getWaveManager().getWaveNumber());
        attackersLeftLabel.setText("Attackers Left: " + model.getWaveManager().getAttackersToSpawn());
        resourcesLabel.setText(String.valueOf(model.getResourceManager().getResources()));
        updateDefenderPanelButtons();
    }

    @Override
    public void onWaveComplete(int waveReward) {
        startWaveButton.setEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initializeComponents() {
        initializeWaveLabels();
        initializeResourcePanel();
        initializeButtons();

        add(resourcePanel);

        // Add a panel for defender selection
        defenderPanel = new DefenderPanel(inputObservers);
        add(defenderPanel);

        add(startWaveButton);
        add(resetGameButton);

        add(wavePanel);
    }

    private void initializeButtons(){
        startWaveButton = ImageButtonFactory.createImageButton("/images/buttons/startBtn.png", 100, 50);
        resetGameButton = ImageButtonFactory.createImageButton("/images/buttons/resetBtn.png", 100, 50);
    }

    private void initializeWaveLabels(){
        waveLabel = new JLabel("Wave: 0");
        attackersLeftLabel = new JLabel("Attackers Left: 0");
        waveLabel.setForeground(Color.WHITE);
        attackersLeftLabel.setForeground(Color.WHITE);
        wavePanel = new JPanel();
        wavePanel.setOpaque(false);
        wavePanel.setLayout(new BorderLayout());
        wavePanel.add(waveLabel, BorderLayout.NORTH);
        wavePanel.add(attackersLeftLabel, BorderLayout.SOUTH);
        wavePanel.setPreferredSize(new Dimension(260, 50));
    }

    private void initializeResourcePanel(){
        resourcePanel = new JPanel();
        resourcePanel.setOpaque(false);
        resourcePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Load the resource coin image and add it to the panel
        Image image = ImageLoader.loadResizedImage("/images/smurfCoin.png", 60, 60);
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        resourcePanel.add(imageLabel);

        // Add the resource count label to the panel
        resourcesLabel = new JLabel(String.valueOf(model.getResourceManager().getResources()));
        resourcesLabel.setForeground(Color.WHITE);
        resourcesLabel.setPreferredSize(new Dimension(90, 50));
        resourcePanel.add(resourcesLabel);
    }

    private void initializeListeners() {
        startWaveButton.addActionListener(e -> {
            startWaveButton.setEnabled(false); // Disable the button after starting the wave
            for (InputObserver observer : inputObservers) {
                observer.onStartWaveClicked();
            }
        });

        resetGameButton.addActionListener(e -> {
            for (InputObserver observer : inputObservers) {
                observer.onResetGameClicked();
            }
        });
    }

    private void updateDefenderPanelButtons() {
        int resources = model.getResourceManager().getResources(); // get the current resources from the model
        for (Map.Entry<JButton, DefenderType> entry : defenderPanel.getButtonMap().entrySet()) {
            JButton button = entry.getKey();
            DefenderType type = entry.getValue();
            button.setEnabled(resources >= type.getCost());
        }
    }
}
