package edu.umb.CS681.hw09;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class AircraftRunnable implements Runnable {
    private final Aircraft aircraft;

    public AircraftRunnable(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public void run() {
        double newLat = 0.0;
        double newLong = 0.0;
        double newAlt = 0.0;

        // Set the position of the aircraft
        aircraft.setPosition(newLat, newLong, newAlt);

        // Get the current position of the aircraft
        Position newPosition = aircraft.getPosition();
        System.out.println("Current position: " + newPosition);
    }
}



