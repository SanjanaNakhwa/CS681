package edu.umb.CS681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            lock.lock();
            try {
                // Stop generating prime numbers if done==true
                if(done){
                    System.out.println("Stopped generating prime numbers.");
                    this.primes.clear();
                    break;
                }
                if( isPrime(n) ){ this.primes.add(n); }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        gen.setDone();
        System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
    }
}