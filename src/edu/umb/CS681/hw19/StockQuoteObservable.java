package edu.umb.CS681.hw19;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent> {
    private Map<String, Double> quoteMap = new HashMap<>();
    private Lock lockTQ = new ReentrantLock();

    public void changeQuote(String t, double q) {
        lockTQ.lock();
        try {
            quoteMap.put(t, q);
        } finally {
            lockTQ.unlock();
        }

        notifyObservers(new StockEvent(t, q));
    }
}