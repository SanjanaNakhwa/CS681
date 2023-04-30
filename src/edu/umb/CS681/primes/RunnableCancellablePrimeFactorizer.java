package edu.umb.CS681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend,from,to);
    }

    public void setDone(){

         lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
       
    }

    public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
            lock.lock();
            try{
                if( divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }
                }
            }
            finally {
                lock.unlock();
            }
		}
	}

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(30,2,100);
        Thread thread = new Thread(gen);
        thread.start();
        gen.generatePrimeFactors();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.factors.forEach( (Long primeFactor)-> System.out.println(primeFactor + ", ") );
        gen.setDone();
        System.out.println("\n" + gen.factors.size() + " prime factors are found.");
    }
}