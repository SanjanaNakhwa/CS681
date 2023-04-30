package edu.umb.CS681.primes;

import java.util.LinkedList;

// Generates prime factors of a given number (dividend)
// Factorization is performed in the range of 2 and Math.sqrt(dividend)
//
// When only one thread is used for factorization, create a Runnable as follows:
//     new RunnablePrimeFactorizer(dividend, 2, (long)Math.sqrt(dividend));
// The thread performs factorization for the entire range of 2 and sqrt(dividend). 
//
// When two threads are used for factorization, create two Runnables as follows:
//     new RunnablePrimeFactorizer(dividend, 2, (long)Math.sqrt(dividend)/2);
//     new RunnablePrimeFactorizer(dividend, 1+(long)Math.sqrt(dividend)/2, (long)Math.sqrt(dividend));

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	
	public RunnablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException(
				"from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
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
	}
	
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().threadId() + " generated " + factors);
	}

	public static void main(String[] args) {
		// Factorization of 36 with a separate thread
		System.out.println("Factorization of 36");
		RunnablePrimeFactorizer runnable = new RunnablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.threadId() + 
			" performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
		
		// Factorization of 84 with two threads
		System.out.println("Factorization of 84");
		LinkedList<RunnablePrimeFactorizer> runnables = new LinkedList<RunnablePrimeFactorizer>();
		LinkedList<Thread> threads = new LinkedList<Thread>();

		runnables.add( new RunnablePrimeFactorizer(84, 2, (long)Math.sqrt(84)/2 ));
		runnables.add( new RunnablePrimeFactorizer(84, 1+(long)Math.sqrt(84)/2, (long)Math.sqrt(84) ));
		
		thread = new Thread(runnables.get(0));
		threads.add(thread);
		System.out.println("Thread #" + thread.threadId() + 
			" performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());
		
		thread = new Thread(runnables.get(1));
		threads.add(thread);
		System.out.println("Thread #" + thread.threadId() + 
			" performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());
		
		threads.forEach( (t)->t.start() );
		threads.forEach( (t)->{	try{t.join();}
								catch(InterruptedException e){e.printStackTrace(); }} );
		
		LinkedList<Long> factors = new LinkedList<Long>();
		runnables.forEach( (factorizer) -> factors.addAll(factorizer.getPrimeFactors()) );
		System.out.println("Final result: " + factors + "\n");
		
		runnables.clear();
		threads.clear();
		
		// Factorization of 2489 with two threads
		System.out.println("Factorization of 2489");
		runnables.add( new RunnablePrimeFactorizer(2489, 2, (long)Math.sqrt(2489)/2 ));
		runnables.add( new RunnablePrimeFactorizer(2489, 1+(long)Math.sqrt(2489)/2, (long)Math.sqrt(2489) ));
		
		thread = new Thread(runnables.get(0));
		threads.add(thread);
		System.out.println("Thread #" + thread.threadId() + 
			" performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());
		
		thread = new Thread(runnables.get(1));
		threads.add(thread);
		System.out.println("Thread #" + thread.threadId() + 
			" performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());
		
		threads.forEach( (t)->t.start() );
		threads.forEach( (t)->{	try{t.join();}
								catch(InterruptedException e){e.printStackTrace(); }} );
		
		LinkedList<Long> factors2 = new LinkedList<Long>();
		runnables.forEach( (factorizer) -> factors2.addAll(factorizer.getPrimeFactors()) );
		System.out.println("Final result: " + factors2);		
	}
}
