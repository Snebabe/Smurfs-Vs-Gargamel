package com.group9.model;

public class GameManager {
    private Model model;
    private boolean running = true;

    public GameManager(Model model) {
        this.model = model;
    }

    public void start() {
        Thread gameLoop = new Thread(() -> {
            final int TICKS_PER_SECOND = 60;
            final long MS_PER_TICK = 1000 / TICKS_PER_SECOND;

            while (running) {
                long startTime = System.currentTimeMillis();

                // Update the model
                model.update();

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
