package edu.umb.CS681.hw03;

import java.util.HashMap;

public class DJIAWkSummaryObservable extends Observable<WkSummary> {
    private HashMap<Integer, DSummary> map = new HashMap<>();
    private int count = 0;

    public HashMap<Integer, DSummary> getMap() {
        return map;
    }

    public void addSummary(DSummary dSummary) {
        map.put(++count, dSummary);
    }
}
