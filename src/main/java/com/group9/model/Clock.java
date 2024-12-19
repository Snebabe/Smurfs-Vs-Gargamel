package com.group9.model;

import java.util.HashMap;
import java.util.Map;

/*
 * Controls the passage of time in the game, managing the tick rate and scheduling
 * periodic updates for observers. Ensures the game loop runs at a consistent pace.
 */

public class Clock{
    private final int TICKS_PER_SECOND;
    private final long MS_PER_TICK;
    private boolean paused;  // Flag to control the clock's state (paused or running)
    private final Map<Observer,TickCounter> observers; // Map of observers and their associated tick intervals

    public Clock(int TICKS_PER_SECOND) {
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
        this.MS_PER_TICK = 1000 / TICKS_PER_SECOND;
        this.paused = false; // Initially not paused
        this.observers = new HashMap<>();
    }

    // Add an observer that will update after a specified number of seconds
    public void addObserver(Observer observer, float seconds) {
        observers.put(observer, new TickCounter(seconds, TICKS_PER_SECOND));
    }

    // Start the game loop that runs every tick
    public void start() {
        Thread gameLoop = new Thread(() -> {

            // Main loop running until the clock is paused
            while (!paused) {
                long startTime = System.currentTimeMillis(); // Track the start time for tick timing
                for (Observer observer : observers.keySet()) {
                    TickCounter tickCounter = observers.get(observer); // Get the observer's tick counter

                    // Check if the observer's tick counter has reached its interval
                    if (tickCounter.getTicks() == tickCounter.getTickInterval()) {
                        observer.update();
                        tickCounter.reset();
                    } else {
                        tickCounter.increment();
                    }
                }

                // Wait to maintain consistent update rate
                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = MS_PER_TICK - elapsedTime;

                // Ensure the thread sleeps for the right amount of time to maintain tick consistency
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameLoop.start(); // Start the game loop thread
    }

}
