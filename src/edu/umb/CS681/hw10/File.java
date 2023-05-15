package edu.umb.CS681.hw10;


import java.time.LocalDateTime;

public class File extends FSElement{
    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		parent.appendChild(this);
	}

	public boolean isDirectory() { 
		lock.lock();
		try{
			return false;
		}
		finally{
            lock.unlock();
		}	
	}

	public boolean isFile() {
		
		lock.lock();
		try{
			return true;
		}
		finally{
            lock.unlock();
		}
	}

	public boolean isLink() {

		lock.lock();
		try{
			return false;
		}
		finally{
			lock.unlock();
		}
	}
}