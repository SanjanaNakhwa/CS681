package edu.umb.CS681.hw02;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarPriceResultHolder {

    private int numCarExamined = 0;
    private double average = 0.0;

    public void accumulate(double price) {
        numCarExamined++;
        average += price;
    }
    
    public void combine(CarPriceResultHolder other) {
        numCarExamined += other.numCarExamined;
        average += other.average;
    }
    
    public int getNumCarExamined() {
        return numCarExamined;
    }

    
    public double getAverage() {
        return numCarExamined > 0 ? average / numCarExamined : 0.0;
    }

    
}



