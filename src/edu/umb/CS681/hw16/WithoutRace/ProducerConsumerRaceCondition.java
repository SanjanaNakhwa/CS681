package edu.umb.CS681.hw16.WithoutRace;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerRaceCondition {
    public static void main(String[] args) {
        Stock stock = new Stock(3); // Shared Stock with a capacity of 3


        Thread[] producerThreads = new Thread[5];
        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i] = new Thread(new Producer(stock,i));
            producerThreads[i].start();
        }

        Thread[] consumerThreads = new Thread[5];
        for (int i = 0; i < consumerThreads.length; i++) {
            consumerThreads[i] = new Thread(new Consumer(stock,i));
            consumerThreads[i].start();
        }


    }
}
