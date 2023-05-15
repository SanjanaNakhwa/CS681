package edu.umb.CS681.hw11;

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
            try {
                // Pick a random file in the folder
                File[] files = folderPath.toFile().listFiles();
                if (files.length == 0) {
                    System.out.println("No files in folder, sleeping for 5 seconds.");
                    Thread.sleep(5000);
                    continue;
                }
                Path file = files[random.nextInt(files.length)].toPath();

                // Call increment() and/or getCount() for the file
                counter.increment(file);
                int count = counter.getCount(file);

                // Sleep for a few seconds
                System.out.println("File " + file + " accessed " + count + " times.");
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                System.out.println("Interrupted, exiting.");
                return;
            }
        }
    }
}

