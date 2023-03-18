package edu.umb.CS681.hw03;

public interface Observer<WkSummary> {
    public void update(Observable<WkSummary> sender, WkSummary event);
}