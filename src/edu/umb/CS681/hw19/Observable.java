package edu.umb.CS681.hw19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable<T> {
    private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();

    public void addObserver(Observer<T> o) {
        observers.add(o);
    }

    public void clearObservers() {
        observers.clear();
    }

    public List<Observer<T>> getObservers() {
        return new ArrayList<>(observers);
    }

    public int countObservers() {
        return observers.size();
    }

    public void removeObserver(Observer<T> o) {
        observers.remove(o);
    }

    public void notifyObservers(T event) {
        observers.forEach(observer -> observer.update(this, event));
    }
}
