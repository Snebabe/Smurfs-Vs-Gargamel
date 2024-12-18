package com.group9.view;

import com.group9.controller.InputObserver;
import com.group9.model.Clock;
import com.group9.model.GameOverListener;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackerType;
import com.group9.model.entities.EntityConfiguration;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.entities.projectiles.ProjectileType;
import com.group9.view.panels.*;
import com.group9.view.services.FontLoader;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame implements Observer, GameOverListener {
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private StartPanel startPanel;
    private AnimationHandler animationHandler;
    private GameOverPanel gameOverPanel;
    private HelpPanel helpPanel;
    private VillagePanel villagePanel;
    private Font font;

    private final List<InputObserver> inputObservers = new ArrayList<>();

    public View(int WIDTH, int HEIGHT, Model model, Clock clock) {
        // Set up the JFrame

        this.setTitle("Smurfs vs. Gargamel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WIDTH+200, HEIGHT+200));
        this.setLayout(new BorderLayout());
        this.animationHandler = new AnimationHandler(clock.getTicksPerSecond());
        this.setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f));
        this.villagePanel = new VillagePanel();
        villagePanel.setPreferredSize(new Dimension(WIDTH/(model.getLaneSize()+1), HEIGHT));

        initializeAnimationHandlers(animationHandler);

        // Load custom font using FontLoader
        font = FontLoader.loadFont("/GROBOLD.ttf");
        UIManager.put("Label.font", font.deriveFont(Font.BOLD, 26));
        UIManager.put("Button.font", font.deriveFont(Font.BOLD, 16));
        UIManager.put("TextField.font", font.deriveFont(Font.BOLD, 26));
        UIManager.put("TextArea.font", font.deriveFont(Font.BOLD, 26));

        // Initialize components
        gamePanel = new GamePanel(model, animationHandler, inputObservers);
        controlPanel = new ControlPanel(model, inputObservers, "/images/backgrounds/controlPanelBg.jpg");
        startPanel = new StartPanel(e -> switchToNormalView(), e -> switchToHelpView());
        helpPanel = new HelpPanel(e -> switchToStartView());

        this.add(startPanel, BorderLayout.CENTER);

        clock.addObserver(this,0);
        clock.addObserver(animationHandler, 0);
        model.addGameOverListener(this);

        this.setVisible(true);
    }

    private void switchToNormalView() {
        this.remove(startPanel);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(villagePanel, BorderLayout.WEST);
        this.add(gamePanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void switchToEndView(int wavesCompleted) {
        this.gameOverPanel = new GameOverPanel(wavesCompleted, e -> startNewGame(), e -> quitGame(), this.font);

        this.remove(controlPanel);
        this.remove(gamePanel);
        this.remove(villagePanel);

        this.add(gameOverPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void switchToHelpView() {
        this.remove(startPanel);
        this.add(helpPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void switchToStartView() {
        this.getContentPane().removeAll();
        this.add(startPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    // Closes the Game Over panel and switches back to the normal view
    // The game will be reset by the model automatically after loss
    private void startNewGame() {
        this.remove(gameOverPanel);
        switchToNormalView();
    }

    private void quitGame() {
        System.exit(0);
    }

    public void initializeAnimationHandlers(AnimationHandler animationHandler) {
        for(DefenderType defenderType : EntityConfiguration.getDefenderTypes()) {
            animationHandler.registerEntityAnimations(
                    defenderType,
                    EntityState.IDLE,
                    "/images/defenders/" + defenderType.getName().toLowerCase() + "/idle/"
            );

            animationHandler.registerEntityAnimations(
                    defenderType,
                    EntityState.ATTACK,
                    "/images/defenders/" + defenderType.getName().toLowerCase() + "/attack/"
            );
        }

        for(AttackerType attackerType: EntityConfiguration.getAttackerTypes()){
            animationHandler.registerEntityAnimations(
                    attackerType,
                    EntityState.ATTACK,
                    "/images/attackers/" + attackerType.getName().toLowerCase() + "/attack/"
            );
            animationHandler.registerEntityAnimations(
                    attackerType,
                    EntityState.MOVE,
                    "/images/attackers/" + attackerType.getName().toLowerCase() + "/move/"
            );
        }

        for(ProjectileType projectileType: EntityConfiguration.getProjectileTypes()){
            animationHandler.registerProjectileAnimations(
                    projectileType,
                    EntityState.MOVE,
                    "/images/projectiles/" + projectileType.getName().toLowerCase() + "/"
            );
        }

    }

    @Override
    public void update() {
        gamePanel.update();
        controlPanel.update();
        villagePanel.update();
    }

    public void addInputObserver(InputObserver observer) {
        inputObservers.add(observer);
    }

    @Override
    public void onGameOver(int wavesSurvived) {
        switchToEndView(wavesSurvived);
    }
}
