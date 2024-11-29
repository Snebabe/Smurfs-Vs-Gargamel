package com.group9.model;

import com.group9.controller.Clock;
import com.group9.controller.Observer;
import com.group9.view.MainView;

import java.util.HashSet;
import java.util.Set;

public class GameManager {
    private Model model;
    private boolean running = true;
    private final Set<Observer> observers;
    private final Clock clock;
    public GameManager(Model model, MainView view) {
        this.model = model;
        this.clock = new Clock();
        observers = new HashSet<>();

        observers.add(view);
    }
    public void start() {
        Thread gameLoop = new Thread(() -> {
            final int TICKS_PER_SECOND = 20;
            final long MS_PER_TICK = 1000 / TICKS_PER_SECOND;

            while (running) {
                long startTime = System.currentTimeMillis();
                for(Observer o: observers) {
                    o.update();
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



    public void stop() {
        running = false;
    }
}
