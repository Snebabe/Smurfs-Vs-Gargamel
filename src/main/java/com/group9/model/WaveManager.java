package com.group9.model;

import com.group9.model.entities.attackers.AttackEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveManager {private int waveNumber;
    private int waveSize;
    private int waveReward;
    private int attackersToSpawn;
    private int spawnInterval;
    private int ticksSinceLastSpawn;
    private AttackEntityFactory factory;
    private List<Lane> lanes;
    private List<WaveCompleteListener> listeners;
    private Board board;

    public WaveManager(AttackEntityFactory factory, Board board) {
        this.waveNumber = 0;
        this.waveSize = 50;
        this.waveReward = 50;
        this.factory = factory;
        this.board = board;
        this.lanes = board.getLanes();
        this.spawnInterval = 25; // Set the interval to spawn attackers
        this.ticksSinceLastSpawn = 0;
        this.listeners = new ArrayList<>();
    }

    public void startWave() {
        waveNumber++;
        waveSize += 2;
        waveReward += 50;
        spawnInterval -= 10;
        attackersToSpawn = waveSize;
        ticksSinceLastSpawn = 0;
    }

    public void resetWaveManager(Board board) {
        waveNumber = 0;
        waveSize = 0;
        waveReward = 50;
        spawnInterval = 100;
        attackersToSpawn = 0;
        ticksSinceLastSpawn = 0;
        this.board = board;
    }

    public void update() {
        if (attackersToSpawn > 0) {
            ticksSinceLastSpawn++;
            if (ticksSinceLastSpawn >= spawnInterval) {
                spawnAttackerRandomly();
                attackersToSpawn--;
                ticksSinceLastSpawn = 0;
            }
        } else {
            checkWaveCompletion();
        }
    }

    private void spawnAttackerRandomly() {
        Random random = new Random();
        int randomLaneIndex = random.nextInt(lanes.size());
        Lane selectedLane = lanes.get(randomLaneIndex);
        selectedLane.addAttacker(factory.createRandomAttacker());
    }


    // Observer management
    public void addWaveCompleteListener(WaveCompleteListener listener) {
        listeners.add(listener);
    }

    public void removeWaveCompleteListener(WaveCompleteListener listener) {
        listeners.remove(listener);
    }

    // Notify observers when a wave is complete
    private void notifyWaveComplete() {
        for (WaveCompleteListener listener : listeners) {
            listener.onWaveComplete();
        }
    }

    private void checkWaveCompletion() {
        boolean allAttackersCleared = true;
        for (Lane lane : board.getLanes()) {
            if (!lane.getAttackers().isEmpty()) {
                allAttackersCleared = false;
                break;
            }
        }
        if (allAttackersCleared) {
            notifyWaveComplete();
        }
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public int getWaveSize() {
        return waveSize;
    }

    public int getAttackersToSpawn() {
        return attackersToSpawn;
    }
    public int getWaveReward() {
        return waveReward;
    }
}
