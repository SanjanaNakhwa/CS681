package edu.umb.CS681.hw03;

public class Summary {
    public double open;
    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double close;
    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double high;
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double low;

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    Summary(double open, double close, double high, double low) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
    }
}
