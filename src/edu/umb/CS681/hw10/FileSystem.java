package edu.umb.CS681.hw10;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class FileSystem implements Runnable{
    private static FileSystem fs = null;
    private LinkedList<Directory> rootDirs;
    private static ReentrantLock lock = new ReentrantLock();

    FileSystem() {
        this.rootDirs = new LinkedList<>();
    }

    public static FileSystem getFileSystem() {
        lock.lock();
    try {
        if (fs == null) {
            fs = new FileSystem();
        }
        return fs;
    } 
    finally {
        lock.unlock();
    }
    }

    public LinkedList<Directory> getRootDirs() {
        lock.lock();
        try {
            return this.rootDirs;
        } 
        finally {
            lock.unlock();
        }
    }

    public void appendRootDirectory(Directory root) {
        lock.lock();
        try {
            this.rootDirs.add(root);
        } 
         finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}

