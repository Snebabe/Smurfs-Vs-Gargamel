package com.group9.view.panels;

import com.group9.controller.InputObserver;
import com.group9.model.Model;
import com.group9.model.WaveCompleteListener;
import com.group9.view.services.ImageButtonFactory;
import com.group9.view.services.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ControlPanel extends JPanel implements WaveCompleteListener {
    private JButton startWaveButton;
    private JButton resetGameButton;
    private JLabel resourcesLabel;
    private JLabel waveLabel;
    private JLabel attackersLeftLabel;
    private JPanel wavePanel;
    private JPanel resourcePanel;
    private final List<InputObserver> inputObservers;
    private final Model model;

    private final Image backgroundImage;


    public ControlPanel(Model model, List<InputObserver> inputObservers, String backgroundImagePath) {
        this.model = model;
        this.inputObservers = inputObservers;
        this.setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f));
        System.out.println(backgroundImagePath);

        backgroundImage = ImageLoader.loadImage(backgroundImagePath);

        // Register this panel as a WaveCompleteListener
        this.model.getWaveManager().addWaveCompleteListener(this);

        // Set up the layout and visual appearance of the control panel
        setupLayout();

        // Initialize buttons and listeners
        initializeComponents();
        initializeListeners();

        // Initially disable the Start Wave button
        setStartWaveButtonEnabled(false);
    }


    //Paints the background image on the panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Sets up the layout and basic properties of the panel.
     */
    private void setupLayout() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    /**
     * Initializes components for the control panel.
     */
    private void initializeComponents() {

        initializeWaveLabels();
        initializeResourcePanel();
        initializeButtons();

        // Add resource panel
        add(resourcePanel);

        // Add a panel for defender selection
        add(new DefenderPanel(inputObservers));

        // Add buttons to the control panel
        add(startWaveButton);
        add(resetGameButton);

        // Add wave labels
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
    }

    private void initializeResourcePanel(){
        resourcePanel = new JPanel();
        resourcePanel.setOpaque(false);
        resourcePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Load the image and add it to the panel
        Image image = ImageLoader.loadResizedImage("/images/smurfCoin.png", 60, 60);
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        resourcePanel.add(imageLabel);

        // Add the resources label to the panel
        resourcesLabel = new JLabel(String.valueOf(model.getResourceManager().getResources()));
        resourcesLabel.setForeground(Color.WHITE);
        resourcesLabel.setPreferredSize(new Dimension(90, 50));
        resourcePanel.add(resourcesLabel);

    }
    /**
     * Initializes listeners for the buttons.
     */
    private void initializeListeners() {
        // Listener for the Start Wave button
        startWaveButton.addActionListener(e -> {
            setStartWaveButtonEnabled(false); // Disable the button after starting the wave
            for (InputObserver observer : inputObservers) {
                observer.onStartWaveClicked();
            }
        });

        // Listener for the Reset Game button
        resetGameButton.addActionListener(e -> {
            for (InputObserver observer : inputObservers) {
                observer.onResetGameClicked();
            }
        });
    }

    /**
     * Enables or disables the Start Wave button.
     */
    public void setStartWaveButtonEnabled(boolean enabled) {
        startWaveButton.setEnabled(enabled);
    }

    /**
     * Callback method invoked when a wave is completed.
     */
    public void update() {
        waveLabel.setText("Wave: " + model.getWaveManager().getWaveNumber());
        attackersLeftLabel.setText("Attackers Left: " + model.getWaveManager().getAttackersToSpawn());
        resourcesLabel.setText(String.valueOf(model.getResourceManager().getResources()));
    }
    @Override
    public void onWaveComplete(int waveReward) {
        setStartWaveButtonEnabled(true);
    }
}
