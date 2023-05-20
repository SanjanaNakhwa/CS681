package edu.umb.CS681.hw16.WithRace;

class Producer implements Runnable {
    private Stock stock;
    private int id;

    public Producer(Stock stock, int id) {
        this.stock = stock;
        this.id = id;
    }

    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                stock.produce(i,id);

            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}