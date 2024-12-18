package com.group9.model;

/*
 * Interface for listening to wave completion events.
 * Any class that wants to handle actions when a wave is completed
 * should implement this interface and define the onWaveComplete method.
 */

public interface WaveCompleteListener {
    // Could add parameters later (e.g. wave number, wave reward?, etc.)
    void onWaveComplete(int waveReward);
}
