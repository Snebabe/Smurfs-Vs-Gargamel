package com.group9.controller;

import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;

public class Clock {
    private static final int DELAY = 20;
    private final Timer timer;
    private final Set<Observer> observers;


    public Clock() {
        observers = new HashSet<>();
        timer = new Timer(DELAY, e -> {
            for (Observer o: observers)
                o.update();
        });
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void start() {
        timer.start();
    }

}
