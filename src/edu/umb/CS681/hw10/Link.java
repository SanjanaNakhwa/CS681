package edu.umb.CS681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {
    private FSElement target;

    private static ReentrantLock lock = new ReentrantLock();

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    public boolean isDirectory() {
        lock.lock();
        try {
            return false;
        } 
        finally {
            lock.unlock();
        }
    }

    public boolean isFile() {
        lock.lock();
        try {
            return false;
        } 
        
        finally {
            lock.unlock();
        }
    }

    public boolean isLink() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }
    }

    public FSElement getTarget() {
        lock.lock();
        try {
            return this.target;
        } 
        finally {
            lock.unlock();
        }
    }

    public void setTarget(FSElement target) {
        lock.lock();
        try {
            this.target = target;
        } 
        finally {
            lock.unlock();
        }
    }
}
