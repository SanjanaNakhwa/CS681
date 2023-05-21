package edu.umb.CS681.hw17.WithDeadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bridge {
    private Lock lockA;
    private Lock lockB;

    public Bridge() {
        this.lockA = new ReentrantLock();
        this.lockB = new ReentrantLock();
    }

    public void crossBridgeA() {
        lockA.lock();
        System.out.println("Vehicle from Group A acquired lock A and is waiting for lock B.");
        try {
            Thread.sleep(1000); // Simulating time taken to cross the bridge
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockB.lock();
        System.out.println("Vehicle from Group A acquired lock B and crossed the bridge.");
        lockB.unlock();
        lockA.unlock();
    }

    public void crossBridgeB() {
        lockB.lock();
        System.out.println("Vehicle from Group B acquired lock B and is waiting for lock A.");
        try {
            Thread.sleep(1000); // Simulating time taken to cross the bridge
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockA.lock();
        System.out.println("Vehicle from Group B acquired lock A and crossed the bridge.");
        lockA.unlock();
        lockB.unlock();
    }
}


