package edu.umb.CS681.hw18;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static AccessCounter instance;
    private ConcurrentHashMap<Path, Integer> accessCounter = new ConcurrentHashMap<>();
    private static Lock instanceLock = new ReentrantLock();

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
        accessCounter.compute(path, (key, value) -> (value == null) ? 1 : value + 1);
    }

    public int getCount(Path path) {
        return accessCounter.getOrDefault(path, 0);
    }
}
