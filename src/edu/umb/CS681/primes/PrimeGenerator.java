package edu.umb.CS681.primes;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.LinkedList;
import java.util.List;

public class PrimeGenerator {
	protected long from, to;
	protected LinkedList<Long> primes = new LinkedList<Long>();

	public PrimeGenerator(long from, long to){
		if(from >= 1 && to > from){
			this.from = from;
			this.to = to;
		}else{
			throw new RuntimeException("Wrong input values: from=" + from + " to=" + to);
		}
	}
	
	public LinkedList<Long> getPrimes(){ return primes; };
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}
	
	protected boolean isPrime(long n){
		// 1 or lower numbers are not prime. 
		if(n <= 1){ return false; }
		// Even numbers are not prime, except for 2.  
		if( n > 2 && isEven(n) ){ return false; }
		long i;
		// Find a number "i" that can divide "n" 
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        // If such a number "i" is found, n is not prime. Otherwise, n is prime. 
        if (i == 1){ return true; }
        else{ return false; }
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ primes.add(n); }
        }
	}
	
	public static void main(String[] args) {
		// Single-threaded prime generation (with generatePrimes())
		PrimeGenerator gen = new PrimeGenerator(1, 100);
		gen.generatePrimes();
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");

		// Single-threaded prime generation (without using generatePrimes())
		PrimeGenerator gen2 = new PrimeGenerator(1, 100);
		List<Long> primes = LongStream.rangeClosed(gen2.from, gen2.to)
								.filter( (long n)->gen2.isPrime(n) )
								.boxed()
								.collect(Collectors.toList());
		primes.forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + primes.size() + " prime numbers are found.");

		// Single-threaded prime generation (without using generatePrimes())
		PrimeGenerator gen3 = new PrimeGenerator(1, 100);
		long size = LongStream.rangeClosed(gen3.from, gen3.to)
								.filter( (long n)->gen3.isPrime(n) )
								.reduce( 0L, (long count, long n)->{
												System.out.print(n + ", ");
												return ++count;} ); 
		System.out.println("\n" + size + " prime numbers are found.");
								
	}
}
