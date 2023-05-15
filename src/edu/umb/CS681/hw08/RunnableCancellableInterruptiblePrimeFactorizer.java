package edu.umb.CS681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{
	
	
	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend,from, to);
	}
	
	public void generatePrimeFactors() {

		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
            lock.lock();
            try{
				if (done){
					System.out.println("Stopped generating prime Factors.");
					this.factors.clear();
					break;
				}

                if( divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }

                if(dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                }
				else {
                    if(divisor==2){ divisor++; }
                    else{ divisor += 2; }
                }
            }
            finally {
                lock.unlock();
            }

			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer gen =
				new RunnableCancellableInterruptiblePrimeFactorizer(30, 2, 100);
		Thread thread = new Thread(gen);
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.factors.forEach( (Long factor)-> System.out.println(factor + ", ") );
		gen.setDone();
		System.out.println("\n" + gen.factors.size() + " Prime Factors ");
	}

}
