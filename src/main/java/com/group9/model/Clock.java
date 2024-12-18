package com.group9.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Clock{
    private final int TICKS_PER_SECOND;
    private final long MS_PER_TICK;
    private boolean paused;
    private final Map<Observer,TickCounter> observers;

    public Clock(int TICKS_PER_SECOND) {
        this.TICKS_PER_SECOND = TICKS_PER_SECOND;
        this.MS_PER_TICK = 1000 / TICKS_PER_SECOND;
        this.paused = false;
        this.observers = new HashMap<>();
    }

    public void addObserver(Observer observer, float seconds) {
        observers.put(observer, new TickCounter(seconds, TICKS_PER_SECOND));
    }

    public int getTicksPerSecond() {
        return TICKS_PER_SECOND;
    }

    public void start() {
        Thread gameLoop = new Thread(() -> {

            while (!paused) {
                long startTime = System.currentTimeMillis();
                for (Observer observer : observers.keySet()) {
                    TickCounter tickCounter = observers.get(observer);

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

}
