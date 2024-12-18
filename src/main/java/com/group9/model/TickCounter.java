package com.group9.model;

public class TickCounter {

    private int tickInterval;
    private int tickCounter;
    public TickCounter(float seconds, int TICKS_PER_SECOND) {
        this.tickInterval = (int) (seconds*TICKS_PER_SECOND);
        this.tickCounter = 0;
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
