package edu.umb.CS681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public boolean isDirectory() {
        lock.lock();
		
        try {
            return true;
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
            return false;
        } 
		
		finally {
            lock.unlock();
        }
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();

        try {
            return this.children;
        } 
		
		finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.lock();

        try {
            this.children.add(child);
            child.setParent(this);
        } 

		finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();

        try {
            return this.children.size();
        } 
		finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();

        try {
            LinkedList<Directory> dirList = new LinkedList<>();
            for (FSElement fsElement : this.children) {
                if (fsElement.isDirectory()) {
                    dirList.add((Directory) fsElement);
                }
            }
            return dirList;
        }
		finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();

        try {
            LinkedList<File> files = new LinkedList<>();
            for (FSElement fsElement : this.children) {
                if (!fsElement.isDirectory()) {
                    files.add((File) fsElement);
                }
            }
            return files;
        }
		finally {
            lock.unlock();
        }
    }

    public int getTotalSize() {
        lock.lock();

        try {
            int totalSize = 0;

            for (FSElement fsElement : this.children) {
                if (fsElement.isDirectory()) {
                    totalSize += ((Directory) fsElement).getTotalSize();
                } else {
                    totalSize += fsElement.getSize();
                }
            }

            return totalSize;
        } 

		finally {
            lock.unlock();
        }
    }
}
