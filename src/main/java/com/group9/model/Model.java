package com.group9.model;

import com.group9.model.managers.AttackManager;
import com.group9.model.board.Board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.defenders.DefenceEntity;
import com.group9.model.entities.characters.defenders.DefenderType;
import com.group9.model.managers.*;
import com.group9.model.managers.MoveManager;
import com.group9.model.observers.ClockObserver;
import com.group9.model.observers.GameOverObserver;
import com.group9.model.services.GetAllPositionsService;
import java.util.*;



/**
 * Central facade class that manages the overall game logic. Initializes and coordinates various managers
 * such as WaveManager, AttackManager, ResourceManager, etc.
 * Responsible for executing game cycles.
 */
public class Model implements ClockObserver {

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

    /**
     * Constructs a Model with the specified ticks per second, lane amount, and lane size.
     *
     * @param TICKS_PER_SECONDS the number of ticks per second
     * @param laneAmount the number of lanes in the game
     * @param laneSize the size of each lane
     */
    public Model(int TICKS_PER_SECONDS, int laneAmount, int laneSize) {
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        board = new Board(laneAmount, laneSize);
        initializeManagers();
        registerObservers();
    }

    /**
     * Runs a game cycle each tick
     */
    public void update() {
        if (gameStateManager.isGameOver()) {
            resetGame();
        }

        waveManager.update();
        moveManager.update();
        attackManager.update();
        projectileManager.handleProjectilesCollision();
    }

    private void initializeManagers() {
        waveManager = new WaveManager(board, TICKS_PER_SECONDS);
        attackManager = new AttackManager(board, TICKS_PER_SECONDS);
        gameStateManager = new GameStateManager(board, waveManager);
        resourceManager = new ResourceManager();
        defenderManager = new DefenderManager(board, resourceManager);
        moveManager = new MoveManager(board, TICKS_PER_SECONDS);
        projectileManager = new ProjectileManager(board);
    }

    private void registerObservers() {
        waveManager.addWaveCompleteObserver(resourceManager);
        attackManager.addAttackDeathObserver(resourceManager);
        projectileManager.addAttackDeathObserver(resourceManager);
    }

    public void resetGame() {
        board.resetBoard();
        waveManager.resetWaveManager();
        resourceManager.resetResources();
    }

    public void addGameOverObserver(GameOverObserver observer) {
        gameStateManager.addGameOverObserver(observer);
    }

    public void startWave() {
        waveManager.startWave();
    }

    public void placeDefender(DefenderType defenderType, Position position) {
        defenderManager.placeDefender(defenderType, position);
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public Board getBoard() {
        return board;
    }

    public WaveManager getWaveManager() {
        return waveManager;
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

}
