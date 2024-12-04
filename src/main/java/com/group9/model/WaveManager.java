package com.group9.model;

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

    public WaveManager(AttackEntityFactory factory, Board board) {
        this.waveNumber = 0;
        this.waveSize = 0;
        this.waveReward = 50;
        this.factory = factory;
        this.lanes = board.getLanes();
        this.spawnInterval = 100; // Set the interval to spawn attackers
        this.ticksSinceLastSpawn = 0;
    }

    public void startWave() {
        waveNumber++;
        waveSize += 5;
        waveReward += 50;
        spawnInterval -= 10;
        attackersToSpawn = waveSize;
        ticksSinceLastSpawn = 0;
    }

    public void update() {
        if (attackersToSpawn > 0) {
            ticksSinceLastSpawn++;
            if (ticksSinceLastSpawn >= spawnInterval) {
                spawnAttackerRandomly();
                attackersToSpawn--;
                ticksSinceLastSpawn = 0;
            }
        }
    }

    private void spawnAttackerRandomly() {
        Random random = new Random();
        int randomLaneIndex = random.nextInt(lanes.size());
        Lane selectedLane = lanes.get(randomLaneIndex);
        selectedLane.addAttacker(factory.createRandomAttacker());
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
