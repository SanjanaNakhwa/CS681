package edu.umb.CS681.hw03;
public class CandleStickObserver implements Observer<WkSummary>{
    public void update(Observable<WkSummary> sender, WkSummary event) {
        System.out.println("The weekly summary for DJIA is as follows:");
        System.out.println("open : " + event.open);
        System.out.println("close : " + event.close);
        System.out.println("high : " + event.high);
        System.out.println("low : " + event.low);

    }

}
