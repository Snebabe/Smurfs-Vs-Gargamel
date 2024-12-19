package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import com.group9.model.managers.AttackManager;
import com.group9.model.board.Board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.attackers.AttackEntityFactory;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.managers.*;
import com.group9.model.managers.MoveManager;
import com.group9.model.services.GetAllPositionsService;

import java.util.*;



/*
 * Central class that manages the overall game logic. Coordinates various managers
 * such as WaveManager, AttackManager, ResourceManager, etc., and handles game state.
 * Responsible for updating the game entities and maintaining the flow of the game.
 */

public class Model implements Observer {

    private final int laneAmount;
    private final int laneSize;

    private final Board board;
    private WaveManager waveManager;
    private AttackManager attackManager;
    private DefenderManager defenderManager;
    private GameStateManager gameStateManager;
    private ResourceManager resourceManager;
    private MoveManager moveManager;

    private final int TICKS_PER_SECONDS;
    private ProjectileManager projectileManager;

    public Model(int TICKS_PER_SECONDS, int laneAmount, int laneSize) {
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        board = new Board(laneAmount, laneSize);
        initializeManagers();
        registerObservers(); // Register observers for relevant events
    }

    // Initialize the different game managers
    private void initializeManagers() {
        waveManager = new WaveManager(board, TICKS_PER_SECONDS);
        attackManager = new AttackManager(board, TICKS_PER_SECONDS);
        gameStateManager = new GameStateManager(board, waveManager);
        resourceManager = new ResourceManager();
        defenderManager = new DefenderManager(board, resourceManager);
        moveManager = new MoveManager(board, TICKS_PER_SECONDS);
        projectileManager = new ProjectileManager(board);
    }

    // Register observers for the managers that need to listen for events
    private void registerObservers() {
        waveManager.addWaveCompleteListener(resourceManager);
        attackManager.addAttackDeathObserver(resourceManager);
        projectileManager.addAttackDeathObserver(resourceManager);
    }

    public Board getBoard() {
        return board;
    }

    public void addGameOverListener(GameOverListener listener) {
        gameStateManager.addGameOverListener(listener);
    }

    // Reset the game state and resources
    public void resetGame() {
        board.resetBoard();
        waveManager.resetWaveManager();
        resourceManager.resetResources();
    }

    // Update the game logic, called every game loop tick

    public void update() {
        if (gameStateManager.isGameOver()) {
            resetGame();
        } // Reset game

        // Update all managers
        waveManager.update();
        moveManager.update();
        attackManager.update();
        projectileManager.handleProjectilesCollision();  // Check for projectile collisions
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }
    public AttackManager getAttackManager() {
        return attackManager;
    }

    // Start the next wave of attackers
    public void startWave() {
        waveManager.startWave();
    }

    public int getLaneAmount() {
        return laneAmount;
    }

    public int getLaneSize() {
        return laneSize;
    }

    public Map<AttackEntity, Position> getAllAttackersPosition() {
        return GetAllPositionsService.getAllAttackersPosition(board);
    }

    public Map<DefenceEntity, Position> getAllDefendersPosition() {
        return GetAllPositionsService.getAllDefendersPosition(board);
    }

    public Map<Projectile, Position> getAllProjectilesPosition() {
        return GetAllPositionsService.getAllProjectilesPosition(board);
    }

    // Place a defender at a given position on the board
    public void placeDefender(DefenderType defenderType, Position position) {
        defenderManager.placeDefender(defenderType, position);
    }
}
