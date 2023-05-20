package edu.umb.CS681.hw16.WithoutRace;
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

    public synchronized void produce(int item, int id) {
        try{
            while (items.size() >= maxSize) {
                wait(); // Wait if Stock is full
            }
    
            items.add(item);
            System.out.println("Produced: " + item + " by Producer " + id);
            for(int i:items){
                System.out.print(i +  " ");
            }
            System.out.println();
            notifyAll(); // Notify waiting consumers
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted, exiting.");
       
        }
        catch(ConcurrentModificationException e){
            System.out.println("Race Condition Occured due to Concurrent Modification");

        }
    }


    public synchronized int consume(int id) {
        try{
            while (items.isEmpty()) {
                wait(); // Wait if Stock is empty
            }
            int item = items.remove(0);
            System.out.println("Consumed: " + item + " by Consumer " + id);
            for(int i:items){
                System.out.print(i +  " ");
            }
            System.out.println();
            notifyAll(); // Notify waiting producers
            return item;
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted, exiting.");
            return -1;
       
        }
        catch(ConcurrentModificationException e){
            System.out.println("Race Condition Occured due to Concurrent Modification");
            return -1;

        }
    
    } 


}
