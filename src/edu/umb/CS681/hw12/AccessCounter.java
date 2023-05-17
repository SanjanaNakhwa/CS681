package edu.umb.CS681.hw12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
    private static AccessCounter instance;
    private static final Object instanceLock = new Object();

    private HashMap<Path, Integer> accessCounter = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private AccessCounter() {
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            synchronized (instanceLock) {
                if (instance == null) {
                    instance = new AccessCounter();
                }
            }
        }
        return instance;
    }

    public void increment(Path path) {
        rwLock.writeLock().lock();
        try {
            if (accessCounter.containsKey(path)) {
                int count = accessCounter.get(path);
                accessCounter.put(path, count + 1);
            } else {
                accessCounter.put(path, 1);
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getCount(Path path) {
        rwLock.readLock().lock();
        try {
            if (accessCounter.containsKey(path)) {
                return accessCounter.get(path);
            } else {
                return 0;
            }
        } 
        finally {
            rwLock.readLock().unlock();
        }
    }
}


