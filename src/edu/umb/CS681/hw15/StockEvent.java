package edu.umb.CS681.hw15;

public record StockEvent(String ticker, double quote) {

    public String getTicker() {
        return ticker;
    }

    public double getQuote() {
        return quote;
    }

}
