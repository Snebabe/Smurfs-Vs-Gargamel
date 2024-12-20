package com.group9.model;

import com.group9.model.observers.ClockObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Controls the passage of time in the game, managing the tick rate and scheduling
 * periodic updates for observers. Ensures the game loop runs at a consistent pace.
 */
public class Clock{
    private final int TICKS_PER_SECOND;
    private final long MS_PER_TICK;
    private final Map<ClockObserver,TickCounter> observers;

    public Clock(int TICKS_PER_SECOND) {
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
        MS_PER_TICK = 1000 / TICKS_PER_SECOND;
        observers = new HashMap<>();
    }

    /**
     * Starts the game loop that runs every tick.
     * The loop will continue running until the game is stopped
     */
    public void start() {
        Thread gameLoop = new Thread(() -> {

            while (true) {
                long startTime = System.currentTimeMillis();
                for (ClockObserver observer : observers.keySet()) {
                    TickCounter tickCounter = observers.get(observer);

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
        gameLoop.start();
    }

    public void addObserver(ClockObserver observer, float seconds) {
        observers.put(observer, new TickCounter(seconds, TICKS_PER_SECOND));
    }

}
