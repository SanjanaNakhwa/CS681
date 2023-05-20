package edu.umb.CS681.hw19;


public class ObservableTest {
    public static void main(String[] args) {
        StockQuoteObservable stockObservable = new StockQuoteObservable();
        LineChartObserver lineChartObserver = new LineChartObserver();
        TableObserver tableObserver = new TableObserver();
        Observer3D observer3d = new Observer3D();
        stockObservable.addObserver(observer3d);
        stockObservable.addObserver(tableObserver);
        stockObservable.addObserver(lineChartObserver);


        RequestHandler[] handlers = new RequestHandler[10];
        Thread[] threads = new Thread[10];

        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new RequestHandler(stockObservable);
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        // Set done and interrupt all threads
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].setDone();
            threads[i].interrupt();
        }
    }
}
