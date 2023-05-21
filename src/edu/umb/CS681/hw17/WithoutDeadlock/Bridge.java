package edu.umb.CS681.hw17.WithoutDeadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bridge {
    private Lock lockA;
    private Lock lockB;

    public Bridge() {
        this.lockA = new ReentrantLock();
        this.lockB = new ReentrantLock();
    }

    public synchronized void crossBridgeA() {
        boolean lockAcquiredA = lockA.tryLock();
        if (lockAcquiredA) {
            System.out.println("Vehicle from Group A acquired lockA.");
            try {
                Thread.sleep(1000); // Simulating time taken to cross the bridge
                boolean lockAcquiredB = lockB.tryLock(2000, TimeUnit.MILLISECONDS);
                if (lockAcquiredB) {
                    try{
                        System.out.println("Vehicle from Group A acquired lockB.");
                    }
                    finally{
                    // Continue crossing the bridge...
                    System.out.println("Vehicle from Group A released lockB.");
                    lockB.unlock(); // Release lockB
                    }
                } else {
                    try{
                        System.out.println("Timeout: Vehicle from Group A could not acquire lockB. Retrying...");
                    }
                    finally{
                        lockA.unlock(); // Release lockA
                        Thread.sleep(1000); // Sleep 
                        crossBridgeA(); // Try Again
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Vehicle from Group A released lockA.");
                lockA.unlock(); // Release lockA
            }
        } else {
            System.out.println("Timeout: Vehicle from Group A could not acquire lockA. Retrying...");
            try{
                Thread.sleep(1000); // Sleep for a short duration before retrying
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            crossBridgeA(); // Try Again
        }
    }

    public synchronized void crossBridgeB() {
        boolean lockAcquiredB = lockB.tryLock();
        if (lockAcquiredB) {
            System.out.println("Vehicle from Group B acquired lockB.");
            try {
                Thread.sleep(2000); // Simulating time taken to cross the bridge
                boolean lockAcquiredA = lockA.tryLock(1000, TimeUnit.MILLISECONDS);
                if (lockAcquiredA) {
                    try{
                        System.out.println("Vehicle from Group B acquired lockA.");
                    }
                    finally{
                    // Continue crossing the bridge...
                        System.out.println("Vehicle from Group B released lockA.");
                        lockA.unlock(); // Release lockA
                    }
                } else {
                    try{
                        System.out.println("Timeout: Vehicle from Group B could not acquire lockA. Retrying...");
                    }
                    finally{
                        lockB.unlock(); // Release lockB
                        Thread.sleep(3000); // Sleep 
                        crossBridgeB(); // Try Again
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Vehicle from Group B released lockB.");
                lockB.unlock(); // Release lockB
            }
        } else {
            System.out.println("Timeout: Vehicle from Group B could not acquire lockB. Retrying...");
            try{
                Thread.sleep(3000); // Sleep for a short duration before retrying
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            crossBridgeB(); // Try Again
        }
    }
}
