package com.group9.view;

import com.group9.controller.InputObserver;
import com.group9.model.Clock;
import com.group9.model.GameOverListener;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackerType;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.entities.projectiles.ProjectileType;
import com.group9.view.panels.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame implements Observer, GameOverListener {
    private InfoPanel infoPanel;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private StartPanel startPanel;
    private AnimationHandler animationHandler;
    private GameOverPanel gameOverPanel;

    private final List<InputObserver> inputObservers = new ArrayList<>();

    public View(int WIDTH, int HEIGHT, Model model, Clock clock) {
        // Set up the JFrame

        this.setTitle("Smurfs vs. Gargamel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());

        this.animationHandler = new AnimationHandler();
        initializeAnimationHandlers(animationHandler);


        // Initialize components
        gamePanel = new GamePanel(model, animationHandler, inputObservers);
        controlPanel = new ControlPanel(model, inputObservers, "/images/controlPanelBackground.jpg");
        infoPanel = new InfoPanel(model, "/images/controlPanelBackground.jpg");
        startPanel = new StartPanel(e -> switchToNormalView());

        this.add(startPanel, BorderLayout.CENTER);

        clock.addObserver(this,0);
        clock.addObserver(animationHandler, 1/4f);
        model.addGameOverListener(this);

        this.setVisible(true);
    }

    private void switchToNormalView() {
        this.remove(startPanel);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.EAST);
        this.revalidate();
        this.repaint();
    }

    public void switchToEndView(int wavesCompleted) {
        this.gameOverPanel = new GameOverPanel(wavesCompleted, e -> startNewGame(), e -> quitGame());

        this.remove(controlPanel);
        this.remove(gamePanel);
        this.remove(infoPanel);

        this.add(gameOverPanel, BorderLayout.CENTER);
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
        for(DefenderType defenderType : DefenderType.values()) {
            animationHandler.registerEntityAnimations(
                    defenderType,
                    EntityState.IDLE,
                    "/images/defenders/" + defenderType.toString().toLowerCase() + "/idle/"
            );

            animationHandler.registerEntityAnimations(
                    defenderType,
                    EntityState.ATTACK,
                    "/images/defenders/" + defenderType.toString().toLowerCase() + "/attack/"
            );
        }

        for(AttackerType attackerType: AttackerType.values()){
            animationHandler.registerEntityAnimations(
                    attackerType,
                    EntityState.ATTACK,
                    "/images/attackers/" + attackerType.toString().toLowerCase() + "/attack/"
            );
            animationHandler.registerEntityAnimations(
                    attackerType,
                    EntityState.MOVE,
                    "/images/attackers/" + attackerType.toString().toLowerCase() + "/move/"
            );
        }

        for(ProjectileType projectileType: ProjectileType.values()){
            animationHandler.registerEntityAnimations(
                    projectileType,
                    EntityState.MOVE,
                    "/images/projectiles/" + projectileType.toString().toLowerCase() + "/"
            );
        }

    }

    @Override
    public void update() {
        gamePanel.update();
        infoPanel.update();
    }

    public void addInputObserver(InputObserver observer) {
        inputObservers.add(observer);
    }

    @Override
    public void onGameOver(int wavesSurvived) {
        switchToEndView(wavesSurvived);
    }
}
