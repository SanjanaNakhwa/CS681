package edu.umb.CS681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private Lock lock = new ReentrantLock();
    private Condition visitorsBelowTen = lock.newCondition();


    public void enter() throws InterruptedException {
        lock.lock();
        try {
            while (currentVisitors >= 10) {
                System.out.println("Too many visitors. Please wait for a while!");
                visitorsBelowTen.await();
            }
            currentVisitors++;
            System.out.println("Visitor Entered: " + currentVisitors);
        } 
        finally {
            lock.unlock();
        }
    }

    public void exit() {
        lock.lock();
        try {
            if (currentVisitors >= 0) {
                currentVisitors--;
                System.out.println("Visitor Exited: " + currentVisitors);
            }
            
            visitorsBelowTen.signalAll();
        } 
        finally {
            lock.unlock();
        }
    }

    public int countCurrentVisitors() {
        lock.lock();
        try {
            System.out.println("Visitor Count: " + currentVisitors);
            return currentVisitors;
        } 
        finally {
            lock.unlock();
        }
    }
}
