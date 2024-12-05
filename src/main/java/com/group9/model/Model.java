package com.group9.model;

/*
 * Interface för vad en model måste ha
 * Om det är något varje model måste ha
 * idk
 */

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackEntityFactory;
import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.entities.defenders.DefenceEntityFactory;

import java.util.*;

public class Model {
    // Ha ett board
    // Tillgång till varje cell
    // getters som: getBoard

    private Board board;
    private WaveManager waveManager;
    private AttackManager attackManager;
    private GameStateManager gameStateManager;
    private int laneAmount = 7;
    private int laneSize = 9;
    private boolean gameOver = false;


    public Model() {
        this.board = new Board(laneAmount, laneSize, 100);
        this.waveManager = new WaveManager(new AttackEntityFactory(), board);
        this.attackManager = new AttackManager(board);
        this.gameStateManager = new GameStateManager(this, board);
    }

    public void update() {
        if (this.gameOver) {
            this. = false;
            resetGame();
        } // Reset game

        waveManager.update();
        attackManager.executeAttackCycle();
        gameStateManager.checkGameOverCondition();
    }

    public void gameOver() {
        this.gameOver = true;
        System.out.println("Game Over!"); // Placeholder for actual game-over logic
        // Notify observers, save game state, or display game-over screen if needed
    }

    public void resetGame() {
        this.board = new Board(laneAmount, laneSize, 100);
        waveManager.resetWaveManager(board);
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public void startWave() {
        waveManager.startWave();
    }

    public int getLaneAmount() {
        return this.laneAmount;
    }

    public int getLaneSize() {
        return this.laneSize;
    }

    public Map<AttackEntity, Position> getAllAttackersPosition() {
        Map<AttackEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (AttackEntity attacker : lane.getAttackers()) {
                int col = (int) ((1 - attacker.getLaneProgress()) * lane.getNumberOfCells());
                map.put(attacker, new Position(row, col));
            }
        }
        return map;
    }

    public Map<DefenceEntity, Position> getAllDefendersPosition() {
        Map<DefenceEntity, Position> map = new HashMap<>();
        for (int row = 0; row < board.getLaneAmount(); row++) {
            Lane lane = board.getLanes().get(row);
            for (int col = 0; col < lane.getNumberOfCells(); col++) {
                GridCell cell = lane.getGridCells().get(col);
                if (cell.hasDefender()) {
                    map.put(cell.getDefender(), new Position(row, col));
                }
            }
        }
        return map;
    }

    public void setDefender(DefenderType defender, int row, int col) {
        board.setDefender(new DefenceEntityFactory().createDefender(defender), row, col);
    }

    public boolean isDefenderAt(Position position) {
        return getAllDefendersPosition().containsValue(position);
    }
}
