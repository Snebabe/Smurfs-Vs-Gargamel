package com.group9.model.observers;

/**
 * Interface for listening to wave completion events.
 * Any class that wants to handle actions when a wave is completed
 */
public interface WaveCompleteObserver {
    void onWaveComplete(int waveReward);
}
