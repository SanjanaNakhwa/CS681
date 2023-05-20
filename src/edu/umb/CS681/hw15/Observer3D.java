package edu.umb.CS681.hw15;


public class Observer3D implements Observer<StockEvent> {


    
    public void update(Observable<StockEvent> sender, StockEvent event) {
    
        // Perform 3D visualization update logic
        System.out.println("3D visualization updated with new stock event- Ticker: " + event.getTicker() + ", Quote: " + event.getQuote());

    }
}
