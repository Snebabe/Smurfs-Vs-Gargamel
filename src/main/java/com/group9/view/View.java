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
        controlPanel = new ControlPanel(model, inputObservers);
        infoPanel = new InfoPanel(model);
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


        animationHandler.registerEntityAnimations(
                DefenderType.BOXER,
                EntityState.IDLE,
                "/images/defenders/boxer/idle/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.BOXER,
                EntityState.ATTACK,
                "/images/defenders/boxer/attack/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.ARCHER,
                EntityState.IDLE,
                "/images/defenders/archer/idle/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.ARCHER,
                EntityState.ATTACK,
                "/images/defenders/archer/attack/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.SHROOM,
                EntityState.IDLE,
                "/images/defenders/shroom/idle/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.SHROOM,
                EntityState.ATTACK,
                "/images/defenders/shroom/attack/"
        );


        animationHandler.registerEntityAnimations(
                AttackerType.GARGAMEL,
                EntityState.ATTACK,
                "/images/attackers/gargamel/attack/"
        );
        animationHandler.registerEntityAnimations(
                AttackerType.GARGAMEL,
                EntityState.MOVE,
                "/images/attackers/gargamel/move/"
        );
        animationHandler.registerEntityAnimations(
                AttackerType.FASTGARGAMEL,
                EntityState.ATTACK,
                "/images/attackers/fastgargamel/attack/"
        );
        animationHandler.registerEntityAnimations(
                AttackerType.FASTGARGAMEL,
                EntityState.MOVE,
                "/images/attackers/fastgargamel/move/"
        );

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
