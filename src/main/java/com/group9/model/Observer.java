package com.group9.model;


/*
 * Observer interface used for the observer pattern.
 * Any class that needs to be notified of changes in the game state or other events
 * should implement this interface and define the `update()` method.
 */

public interface Observer {
    void update(); // Method to be called when the object needs to be updated
}
