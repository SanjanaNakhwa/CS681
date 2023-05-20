package edu.umb.CS681.hw16.WithRace;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

class Stock {
    private List<Integer> items;
    private int maxSize;

    public Stock(int maxSize) {
        this.items = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void produce(int item, int id) {
        try{
            while (items.size() >= maxSize) {
                // Simulate some processing time
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            items.add(item);
            System.out.println("Produced: " + item + " by Producer " + id);
            for (int i : items) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        catch(ConcurrentModificationException e){
            System.out.println("Race Condition Occured due to Concurrent Modification");

        }
    }

    public int consume(int id) {
        try{
            while (items.isEmpty()) {
                // Simulate some processing time
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int item = items.remove(0);
            System.out.println("Consumed: " + item + " by Consumer " + id);
            for (int i : items) {
                System.out.print(i + " ");
            }
            System.out.println();
            return item;
        }
        catch(ConcurrentModificationException e){
            System.out.println("Race Condition Occured due to Concurrent Modification");
            return -1;

        }
    }
}