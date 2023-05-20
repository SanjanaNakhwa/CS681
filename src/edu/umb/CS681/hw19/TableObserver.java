package edu.umb.CS681.hw19;

public class TableObserver implements Observer<StockEvent> {

    public void update(Observable<StockEvent> sender, StockEvent event) {
        // Perform table update logic
        System.out.println("Table updated with new stock event- Ticker: " + event.getTicker() + ", Quote: " + event.getQuote());

    }
}
