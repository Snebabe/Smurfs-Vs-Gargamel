package com.group9.model;

import com.group9.model.observers.ClockObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controls the passage of time in the game, managing the tick rate and send update signals
 * each tick for its observers. Ensures the game loop runs at a consistent pace.
 */
public class Clock{
    private final long MS_PER_TICK;
    private final List<ClockObserver> observers = new ArrayList<>();;

    public Clock(int TICKS_PER_SECOND) {
        MS_PER_TICK = 1000 / TICKS_PER_SECOND;
    }

    /**
     * Starts the game loop that runs every tick.
     * The loop will continue running until the game is stopped
     */
    public void start() {
        Thread gameLoop = new Thread(() -> {

            while (true) {
                long startTime = System.currentTimeMillis();
                for (ClockObserver observer : observers) {
                    observer.update();
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

    public void addObserver(ClockObserver observer) {
        observers.add(observer);
    }

}
