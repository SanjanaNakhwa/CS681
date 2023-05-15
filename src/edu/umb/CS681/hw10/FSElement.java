package edu.umb.CS681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;
    
    public static ReentrantLock lock = new ReentrantLock();

    FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent() {
        lock.lock();
        try {
            return this.parent;
        } 
        finally {
            lock.unlock();
        }
    }

    public void setParent(Directory parent) {
        lock.lock();
        try {
            this.parent = parent;
        } 
        finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return name;
        } 
        finally {
            lock.unlock();
        }
    }

    public void setName(String name) {
        lock.lock();
        try {
            this.name = name;
        } 
        finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return this.size;
        } 
        finally {
            lock.unlock();
        }
    }

    public void setSize(int size) {
        lock.lock();
        try {
            this.size = size;
        } 
        finally {
            lock.unlock();
        }
    }

    public LocalDateTime getCreationTime() {
        lock.lock();
        try {

            return creationTime;
        } 
        finally {
            lock.unlock();
        }
    }

    public void setCreationTime(LocalDateTime creationTime) {
        lock.lock();

        try {
            this.creationTime = creationTime;
        } 
        finally {
            lock.unlock();
        }
    }

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isLink();
}
