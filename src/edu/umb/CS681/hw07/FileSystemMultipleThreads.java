package edu.umb.CS681.hw07;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.util.stream.IntStream;


public class FileSystemMultipleThreads {

    public static void main(String[] args) {
        Set<FileSystem> fileSystemSet = Collections.synchronizedSet(new HashSet<>());

        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                FileSystem fileSystem = FileSystem.getFileSystem();
                fileSystemSet.add(fileSystem);
                // Do something with the fileSystem object
            });
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The total number of instances of file system are:" + fileSystemSet.size());
    }
 }
