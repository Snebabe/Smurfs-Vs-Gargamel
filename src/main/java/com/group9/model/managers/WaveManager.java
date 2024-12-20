package com.group9.model.managers;

import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.observers.ClockObserver;
import com.group9.model.observers.WaveCompleteObserver;
import com.group9.model.entities.characters.attackers.AttackEntity;
import com.group9.model.entities.characters.attackers.AttackEntityFactory;
import com.group9.model.entities.characters.attackers.AttackerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Manages the spawning of attackers in waves. Controls the timing of each wave
 * and its size. Also tracks wave rewards and notifies listeners when a wave is complete.
 */
public class WaveManager implements ClockObserver {
    private int waveNumber;
    private int waveSize;
    private int waveReward;
    private int attackersToSpawn;
    private int spawnIntervalInTicks;
    private final int defaultSpawnIntervalInTicks;
    private int ticksSinceLastSpawn;
    private final int TICKS_PER_SECONDS;
    private final List<WaveCompleteObserver> observers;
    private boolean waveCompleted;
    private final Board board;
    private final ProbabilityManager probabilityManager;

    
    /**
     * Constructs a WaveManager with the specified game board, and ticks per second.
     *
     * @param board the game board
     * @param TICKS_PER_SECONDS the number of ticks per second
     */
    public WaveManager(Board board , int TICKS_PER_SECONDS) {
        waveNumber = 0;
        waveSize = 0;
        waveReward = 0;
        this.board = board;
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
        spawnIntervalInTicks = TICKS_PER_SECONDS*5; // Set the interval to spawn attackers
        defaultSpawnIntervalInTicks = spawnIntervalInTicks;
        ticksSinceLastSpawn = 0;
        observers = new ArrayList<>();
        waveCompleted = false;
        probabilityManager = new ProbabilityManager();
    }

    /**
     * Starts a new wave with an increased wave number, size, and reward.
     * Decreases the spawn interval for attackers.
     */
    public void startWave() {
        waveNumber++;
        waveSize += 2;
        waveReward = 300 + (waveNumber -1) * 50; // Start at 300 and increment by 50 each wave
        if(spawnIntervalInTicks > TICKS_PER_SECONDS/2) { // Minimum spawn rate 0.5 seconds
            spawnIntervalInTicks -= TICKS_PER_SECONDS/2; // Decrease the spawn interval by 0.5 seconds each wave;
        }
        attackersToSpawn = waveSize;
        waveCompleted = false;

        // Quick probabilities fix for KNIGHTGARGAMEL, could use improvements.
        // Could specify probabilities (and which wave to spawn from) in a config file or in the AttackerType.
        if (waveNumber == 10) {
            probabilityManager.setProbability("KNIGHTGARGAMEL", 0.05);
        }
    }

    /**
     * Resets the wave manager to its initial state.
     */
    public void resetWaveManager() {
        waveNumber = 0;
        waveSize = 0;
        waveReward = 0;
        attackersToSpawn = 0;
        ticksSinceLastSpawn = 0;
        spawnIntervalInTicks = defaultSpawnIntervalInTicks;
        waveCompleted = false;
        probabilityManager.resetProbabilities();
    }

    /**
     * Each update cycles spawns attackers at intervals and checks for wave completion.
     */
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

    /**
     * Checks if all attackers are cleared from the lanes and notifies listeners if the wave is complete.
     */
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

    /**
     * Spawns an attacker randomly in one of the lanes.
     */
    private void spawnAttackerRandomly() {
        Random random = new Random();
        int randomLaneIndex = random.nextInt(board.getLanes().size());
        Lane selectedLane = board.getLanes().get(randomLaneIndex);

        AttackerType randomType = probabilityManager.getRandomAttackerType();
        AttackEntity attacker = AttackEntityFactory.createAttacker(randomType);
        selectedLane.addAttacker(attacker);
    }

    public void addWaveCompleteObserver(WaveCompleteObserver observer) {
        observers.add(observer);
    }

    public void removeWaveCompleteObserver(WaveCompleteObserver observer) {
        observers.remove(observer);
    }

    private void notifyWaveComplete() {
        if (!waveCompleted) {
            waveCompleted = true;
            for (WaveCompleteObserver observer : observers) {
                observer.onWaveComplete(getWaveReward());
            }
        }

    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public int getAttackersToSpawn() {
        return attackersToSpawn;
    }

    public int getWaveReward() {
        return waveReward;
    }
}
