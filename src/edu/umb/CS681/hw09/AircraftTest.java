
package edu.umb.CS681.hw09;
import java.util.*;


    public class AircraftTest {
        public static void main(String[] args) {
            // Create the first position
            Position pos1 = new Position(40.718, -740.60, 102.0);
    
            // Create the aircraft with the first position
            Aircraft aircraft = new Aircraft(pos1);
    
            // Create the second position
            Position pos2 = new Position(37.779, -162.414, 220.0);
    
            // Create and start the threads
            Thread thread1 = new Thread(() -> {
                // Test higherAltThan method
                if (aircraft.getPosition().higherAltThan(pos2)) {
                    System.out.println("Thread 1: The aircraft is higher than the second position.");
                } else {
                    System.out.println("Thread 1: The aircraft is not higher than the second position.");
                }
    
                // Test northOf method
                if (aircraft.getPosition().northOf(pos2)) {
                    System.out.println("Thread 1: The aircraft is north of the second position.");
                } else {
                    System.out.println("Thread 1: The aircraft is not north of the second position.");
                }
            });
    
            Thread thread2 = new Thread(() -> {
                // Test lowerAltThan method
                if (aircraft.getPosition().lowerAltThan(pos2)) {
                    System.out.println("Thread 2: The aircraft is lower than the second position.");
                } else {
                    System.out.println("Thread 2: The aircraft is not lower than the second position.");
                }
    
                // Test southOf method
                if (aircraft.getPosition().southOf(pos2)) {
                    System.out.println("Thread 2: The aircraft is south of the second position.");
                } else {
                    System.out.println("Thread 2: The aircraft is not south of the second position.");
                }
            });
    
            thread1.start();
            thread2.start();
        }
    }
    


