package com.group9.view;

import com.group9.controller.InputObserver;
import com.group9.model.Clock;
import com.group9.model.Model;
import com.group9.model.Observer;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackerType;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.view.panels.ControlPanel;
import com.group9.view.panels.GamePanel;
import com.group9.view.panels.InfoPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame implements Observer {
    private InfoPanel infoPanel;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private AnimationHandler animationHandler;

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

        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.EAST);


        clock.addObserver(this,0);
        clock.addObserver(animationHandler, 1/3);

        this.setVisible(true);
    }

    public void initializeAnimationHandlers(AnimationHandler animationHandler) {
        /*
        animationHandler.registerEntityAnimations(
                AttackerType.GARGAMEL,
                EntityState.MOVE,
                "//attackers/gargamel/move/"


        );

        animationHandler.registerEntityAnimations(
                AttackerType.GARGAMEL,
                EntityState.ATTACK,
                "/images/attackers/gargamel/attack/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.SHROOM,
                EntityState.MOVE,
                "/images/defenders/fastgargamel/move/"
        );

        animationHandler.registerEntityAnimations(
                DefenderType.SHROOM,
                EntityState.ATTACK,
                "/images/defenders/shroom/attack/"
        );*/

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

    }

    @Override
    public void update() {
        gamePanel.update();
        infoPanel.update();
    }

    public void addInputObserver(InputObserver observer) {
        inputObservers.add(observer);
    }
}
