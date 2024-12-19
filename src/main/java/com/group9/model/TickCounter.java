package com.group9.model;

/*
 * Helper class to track the number of ticks for a specific interval.
 * It is used to manage periodic events by counting ticks and triggering
 * actions when a specified number of ticks (based on seconds) has passed.
 */

public class TickCounter {

    private final int tickInterval;
    private int tickCounter;
    public TickCounter(float seconds, int TICKS_PER_SECOND) {
        tickInterval = (int) (seconds*TICKS_PER_SECOND);
        tickCounter = 0;
    }
    public void increment() {
        tickCounter++;
    }
    public int getTicks() {
        return tickCounter;
    }
    public int getTickInterval() {
        return tickInterval;
    }
    public void reset() {
        tickCounter = 0;
    }
}
