package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import com.group9.model.board.Board;
import com.group9.model.board.GridCell;
import com.group9.model.board.Lane;
import com.group9.model.entities.Projectile;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackEntityFactory;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.defenders.DefenceEntityFactory;
import com.group9.model.entities.defenders.DefenderType;
import com.group9.model.managers.*;

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

    private int TICKS_PER_SECONDS;

    public Model(int TICKS_PER_SECONDS) {
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        initializeBoard();
        initializeManagers();
        registerObservers();
    }

    private void initializeBoard() {
        this.board = new Board(laneAmount, laneSize, cellSize, TICKS_PER_SECONDS);
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(new AttackEntityFactory(), board, TICKS_PER_SECONDS);
        this.attackManager = new AttackManager(board);
        this.gameStateManager = new GameStateManager(board);
        this.resourceManager = new ResourceManager();
        this.positionManager = new PositionManager(board);
        this.defenderManager = new DefenderManager(board, resourceManager);
    }

    private void registerObservers() {
        this.waveManager.addWaveCompleteListener(resourceManager);
        this.attackManager.addAttackDeathOberver(resourceManager);
    }

    public void resetGame() {
        initializeBoard();
        waveManager.resetWaveManager(board);
        attackManager.resetBoard(board);
        gameStateManager.resetBoard(board);
        resourceManager.resetResources();
        positionManager.resetPositionManager(board);
        defenderManager.resetDefenderManager(board, resourceManager);
    }

    public void update() {
        if (gameStateManager.isGameOver()) {
            resetGame();
        } // Reset game

        waveManager.update();
        board.getMoveManager().update();
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
