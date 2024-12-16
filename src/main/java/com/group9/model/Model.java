package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import com.group9.model.managers.AttackManager;
import com.group9.model.board.Board;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackEntityFactory;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.managers.*;
import com.group9.model.managers.MoveManager;

import java.util.*;

public class Model implements Observer {

    private static final int laneAmount = 5;
    private static final int laneSize = 9;
    private static final int cellSize = 100;

    private Board board;
    private PositionManager positionManager;
    private WaveManager waveManager;
    private AttackManager attackManager;
    private DefenderManager defenderManager;
    private GameStateManager gameStateManager;
    private ResourceManager resourceManager;
    private MoveManager moveManager;

    private final int TICKS_PER_SECONDS;
    private ProjectileManager projectileManager;

    public Model(int TICKS_PER_SECONDS) {
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        this.board = new Board(laneAmount, laneSize, cellSize);
        initializeManagers();
        registerObservers();
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(new AttackEntityFactory(), board, TICKS_PER_SECONDS);
        this.attackManager = new AttackManager(board);
        this.gameStateManager = new GameStateManager(board, waveManager);
        this.resourceManager = new ResourceManager();
        this.positionManager = new PositionManager(board);
        this.defenderManager = new DefenderManager(board, resourceManager);
        this.moveManager = new MoveManager(board, TICKS_PER_SECONDS);
        this.projectileManager = new ProjectileManager(board);
    }

    private void registerObservers() {
        this.waveManager.addWaveCompleteListener(resourceManager);
        this.attackManager.addAttackDeathOberver(resourceManager);
        this.projectileManager.addAttackDeathOberver(resourceManager);
    }

    public Board getBoard() {
        return board;
    }

    public void addGameOverListener(GameOverListener listener) {
        this.gameStateManager.addGameOverListener(listener);
    }

    public void resetGame() {
        board.resetBoard();
        waveManager.resetWaveManager();
        resourceManager.resetResources();
    }

    public void update() {
        if (gameStateManager.isGameOver()) {
            resetGame();
        } // Reset game

        waveManager.update();
        moveManager.update();
        projectileManager.handleProjectilesCollision();
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
        return positionManager.getAllAttackersPosition();
    }

    public Map<DefenceEntity, Position> getAllDefendersPosition() {
        return positionManager.getAllDefendersPosition();
    }

    public Map<Projectile, Position> getAllProjectilesPosition() {
        return positionManager.getAllProjectilesPosition();
    }

    public void placeDefender(DefenderType defenderType, Position position) {
        defenderManager.placeDefender(defenderType, position);
    }
}
