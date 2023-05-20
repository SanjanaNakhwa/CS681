package edu.umb.CS681.hw15;


public class LineChartObserver implements Observer<StockEvent> {

    public void update(Observable<StockEvent> sender, StockEvent event) {
        // Perform line chart update logic
        System.out.println("Line chart updated with new stock event - Ticker: " + event.getTicker() + ", Quote: " + event.getQuote());

    }
}    