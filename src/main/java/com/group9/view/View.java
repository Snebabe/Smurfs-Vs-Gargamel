package com.group9.view;

import com.group9.controller.InputObserver;
import com.group9.model.Clock;
import com.group9.model.observers.GameOverObserver;
import com.group9.model.Model;
import com.group9.model.observers.ClockObserver;
import com.group9.model.services.GameContext;
import com.group9.model.entities.EntityState;
import com.group9.model.entities.characters.attackers.AttackerType;
import com.group9.model.entities.EntityConfiguration;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.entities.projectiles.ProjectileType;
import com.group9.view.panels.*;
import com.group9.view.services.FontLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The View class represents the main window of the game, handling the display of various panels
 * and managing the game's fullscreen mode. Updates by observing the game clock and game over events.
 */
public class View extends JFrame implements ClockObserver, GameOverObserver {
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private StartPanel startPanel;
    private GameOverPanel gameOverPanel;
    private HelpPanel helpPanel;
    private VillagePanel villagePanel;
    private Font font;
    private final List<InputObserver> inputObservers = new ArrayList<>();
    private boolean isFullscreen = false;

    /**
     * Constructs a new View with the specified dimensions, model, and clock.
     *
     * @param WIDTH the width of the window
     * @param HEIGHT the height of the window
     * @param model the game model
     * @param clock the game clock
     */
    public View(int WIDTH, int HEIGHT, Model model, Clock clock) {

        // Set up the JFrame
        setTitle("Smurfs vs. Gargamel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH+300, HEIGHT+200));
        setLayout(new BorderLayout());
        setBackground(Color.getHSBColor(0.33f, 1.0f, 0.2f));

        AnimationHandler animationHandler = new AnimationHandler(GameContext.getTicksPerSecond());

        loadCustomFont();
        initializeAnimations(animationHandler);
        initializePanels(model, animationHandler);
        addObservers(clock, model, animationHandler);
        initializeKeyListener();

        setVisible(true);
    }

    /**
     * Initializes the animation handlers for various entity types.
     *
     * @param animationHandler the animation handler
     */
    private void initializeAnimations(AnimationHandler animationHandler) {
        for(DefenderType defenderType : EntityConfiguration.getDefenderTypes()) {
            animationHandler.registerAnimations(
                    defenderType,
                    EntityState.IDLE,
                    "/images/defenders/" + defenderType.getName().toLowerCase() + "/idle/"
            );

            animationHandler.registerAnimations(
                    defenderType,
                    EntityState.ATTACK,
                    "/images/defenders/" + defenderType.getName().toLowerCase() + "/attack/"
            );
        }

        for(AttackerType attackerType: EntityConfiguration.getAttackerTypes()){
            animationHandler.registerAnimations(
                    attackerType,
                    EntityState.ATTACK,
                    "/images/attackers/" + attackerType.getName().toLowerCase() + "/attack/"
            );
            animationHandler.registerAnimations(
                    attackerType,
                    EntityState.MOVE,
                    "/images/attackers/" + attackerType.getName().toLowerCase() + "/move/"
            );
        }

        for(ProjectileType projectileType: EntityConfiguration.getProjectileTypes()){
            animationHandler.registerAnimations(
                    projectileType,
                    EntityState.MOVE,
                    "/images/projectiles/" + projectileType.getName().toLowerCase() + "/"
            );
        }
    }

    public void switchToEndView(int wavesCompleted) {
        gameOverPanel = new GameOverPanel(wavesCompleted, inputObservers, e -> switchToGameView());

        remove(controlPanel);
        remove(gamePanel);
        remove(villagePanel);

        add(gameOverPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void switchToHelpView() {
        remove(startPanel);
        add(helpPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void switchToStartView() {
        getContentPane().removeAll();
        add(startPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    @Override
    public void update() {
        gamePanel.update();
        controlPanel.updateControlPanelState();
        villagePanel.update();
    }

    public void addInputObserver(InputObserver observer) {
        inputObservers.add(observer);
    }

    @Override
    public void onGameOver(int wavesSurvived) {
        switchToEndView(wavesSurvived);
    }

    private void initializeKeyListener() {
        // Add KeyListener for F11 and Escape to toggle fullscreen
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    toggleFullscreen();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && isFullscreen) {
                    toggleFullscreen();
                }
            }
        });
        // Request focus for the JFrame to ensure it receives key events
        setFocusable(true);
        requestFocusInWindow();
    }

    private void toggleFullscreen() {
        isFullscreen = !isFullscreen;
        dispose();
        setUndecorated(isFullscreen);
        if (isFullscreen) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setExtendedState(JFrame.NORMAL);
        }
        setVisible(true);
    }

    private void addObservers(Clock clock, Model model, AnimationHandler animationHandler){
        clock.addObserver(this,0);
        clock.addObserver(animationHandler, 0);
        model.addGameOverObserver(this);
    }

    private void initializePanels(Model model, AnimationHandler animationHandler){
        villagePanel = new VillagePanel(model.getLaneAmount());
        villagePanel.setPreferredSize(new Dimension(getWidth()/(model.getLaneSize()+1), getHeight()));
        gamePanel = new GamePanel(model, animationHandler, inputObservers);
        controlPanel = new ControlPanel(model, inputObservers, "/images/backgrounds/controlPanelBg.jpg");
        startPanel = new StartPanel(e -> switchToGameView(), e -> switchToHelpView());
        helpPanel = new HelpPanel(e -> switchToStartView());
        add(startPanel, BorderLayout.CENTER);
    }

    private void loadCustomFont(){
        // Load custom font using FontLoader
        font = FontLoader.loadFont("/GROBOLD.ttf");
        UIManager.put("Label.font", font.deriveFont(Font.BOLD, 26));
        UIManager.put("Button.font", font.deriveFont(Font.BOLD, 16));
        UIManager.put("TextField.font", font.deriveFont(Font.BOLD, 26));
        UIManager.put("TextArea.font", font.deriveFont(Font.BOLD, 26));
    }

    private void switchToGameView() {
        getContentPane().removeAll();
        add(controlPanel, BorderLayout.SOUTH);
        add(villagePanel, BorderLayout.WEST);
        add(gamePanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
