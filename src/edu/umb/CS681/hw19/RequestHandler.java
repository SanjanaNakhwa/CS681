package edu.umb.CS681.hw19;


import java.util.Random;

public class RequestHandler implements Runnable {
    private volatile boolean done = false;
    private StockQuoteObservable stockObservable;
    private Random random = new Random();
    private String[] tickers = {"AAPL", "GOOG", "TSLA", "AMZN", "MSFT"};

    public RequestHandler(StockQuoteObservable stockObservable) {
        this.stockObservable = stockObservable;
      
    }

    public void setDone() {
        done = true;
    }

    @Override
    public void run() {
        while (!done) {
        
            String ticker = tickers[random.nextInt(tickers.length)];
            double quote = random.nextDouble() * 1000;

            // Update the stock quote in StockQuoteObservable
                stockObservable.changeQuote(ticker, quote);

            // Sleep for a certain period
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted, exiting.");
                break;
            }
        }
    }
}

