package edu.umb.CS681.hw03;

import java.util.ArrayList;
import java.util.List;

public class Observable<WkSummary> {
    List<Observer<WkSummary>> observers = new ArrayList<>();

    public void addObserver(Observer<WkSummary> o) {
        observers.add(o);
    }

    public void removeObserver(Observer<WkSummary> o) {
        observers.remove(o);
    }

    public int countObservers(){
        return observers.size();
    }

    public List<Observer<WkSummary>> getObservers() {
        return this.observers;
    }

    public void clearObservers() {
        this.observers.clear();
    }

    public void notifyObservers(WkSummary event) {
        observers.forEach(
            (observer) ->  {observer.update(this, event);}
        );
    }
}
