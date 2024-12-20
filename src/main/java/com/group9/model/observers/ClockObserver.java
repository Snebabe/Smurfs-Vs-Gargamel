package com.group9.model.observers;


/**
 * Observer interface used to listen for each tick update from the clock.
 * Any class that needs to be notified and updated every tick
 * should implement this interface and define the `update()` method.
 */
public interface ClockObserver {
    void update();
}
