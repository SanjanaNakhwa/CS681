package edu.umb.CS681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static AccessCounter instance;
    private static Lock instanceLock = new ReentrantLock();

    private HashMap<Path, Integer> accessCounter = new HashMap<>();
    private Lock lock = new ReentrantLock();

    private AccessCounter() {
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new AccessCounter();
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public void increment(Path path) {
        lock.lock();
        try {
            if (accessCounter.containsKey(path)) {
                int count = accessCounter.get(path);
                accessCounter.put(path, count + 1);
            } else {
                accessCounter.put(path, 1);
            }
        } finally {
            lock.unlock();
        }
    }

    public int getCount(Path path) {
        lock.lock();
        try {
            if (accessCounter.containsKey(path)) {
                return accessCounter.get(path);
            } else {
                return 0;
            }
        } finally {
            lock.unlock();
        }
    }
}

