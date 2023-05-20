package edu.umb.CS681.hw16.WithRace;

class Consumer implements Runnable {
    private Stock stock;
    private int id;

    public Consumer(Stock stock, int id) {
        this.stock = stock;
        this.id = id;
    }

    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                int item = stock.consume(id);

            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}