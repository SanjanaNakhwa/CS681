package edu.umb.CS681.hw12;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class RequestHandler implements Runnable {
    volatile boolean done = false;
    private AccessCounter counter;
    private Path folderPath;
    private Random random = new Random();

    public RequestHandler(AccessCounter counter, String folderPath) {
        this.counter = counter;
        this.folderPath = Paths.get(folderPath);
    }

    public void setDone(){
         done = true;
    }

    public void run() {
        while (true) {
            
            if (done){  
                break;
            }
            File[] files = folderPath.toFile().listFiles();
            
            Path file = files[random.nextInt(files.length)].toPath();

            // Call increment() and/or getCount() for the file
            counter.increment(file);
            int count = counter.getCount(file);

            System.out.println("File " + file + " accessed " + count + " times.");

            // Sleep 
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                System.out.println("Interrupted, exiting.");
                break;
            }
        }
    }
}

