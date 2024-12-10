package com.group9.model.managers;

import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.Observer;
import com.group9.model.WaveCompleteListener;
import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.attackers.AttackEntityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveManager implements Observer {
    private int waveNumber;
    private int waveSize;
    private int waveReward;
    private int attackersToSpawn;
    private int spawnIntervalInTicks;
    private int ticksSinceLastSpawn;
    private int TICKS_PER_SECONDS;
    private AttackEntityFactory factory;
    private List<WaveCompleteListener> listeners;
    private boolean waveCompleted;
    private Board board;

    public WaveManager(AttackEntityFactory factory, Board board , int TICKS_PER_SECONDS) {
        this.waveNumber = 0;
        this.waveSize = 0;
        this.waveReward = 0;
        this.factory = factory;
        this.board = board;
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        this.spawnIntervalInTicks = TICKS_PER_SECONDS*5; // Set the interval to spawn attackers
        this.ticksSinceLastSpawn = 0;
        this.listeners = new ArrayList<>();
        this.waveCompleted = false;
    }

    public void startWave() {
        waveNumber++;
        waveSize += 3;
        waveReward = 300 + (waveNumber -1) *50; // Start at 300 and increment by 50 each wave
        if(spawnIntervalInTicks > TICKS_PER_SECONDS/2) { // Minimum spawn rate 0.5 seconds
            spawnIntervalInTicks -= TICKS_PER_SECONDS/2; // Decrease the spawn interval by 0.5 seconds each wave;
        }
        attackersToSpawn = waveSize;
        waveCompleted = false;
    }

    public void resetWaveManager(Board board) {
        waveNumber = 0;
        waveSize = 0;
        waveReward = 0;
        attackersToSpawn = 0;
        ticksSinceLastSpawn = 0;
        this.board = board;
        this.waveCompleted = false;
    }

    @Override
    public void update() {
        if (attackersToSpawn > 0) {
            ticksSinceLastSpawn++;
            if (ticksSinceLastSpawn >= spawnIntervalInTicks) {
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
        int randomLaneIndex = random.nextInt(board.getLanes().size());
        Lane selectedLane = board.getLanes().get(randomLaneIndex);
        AttackEntity attacker = factory.createRandomAttacker();
        this.board.addMovable(attacker, selectedLane);
        selectedLane.addAttacker(attacker);
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
        if (!waveCompleted) {
            waveCompleted = true;
            for (WaveCompleteListener listener : listeners) {
                listener.onWaveComplete(getWaveReward());
            }
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
