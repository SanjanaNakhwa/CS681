package edu.umb.CS681.hw02;
import java.util.ArrayList;
import java.util.List;

public class CarClient {

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Maruti", "Swift", 6800, 2009, 50456f));
        cars.add(new Car("Hyundi", "I20", 1000, 2018, 30560f));
        cars.add(new Car("Tesla", "Model S", 3400, 2021, 40000f));
        cars.add(new Car("Tesla", "Model X", 2100, 2017, 50000f));
        cars.add(new Car("Accord", "Honda", 3000, 2016, 26000f));
        cars.add(new Car("Accent", "Hyundai", 6500, 2013, 16700f));
        cars.add(new Car("BMW", "340", 2000, 2019, 10200f));
        cars.add(new Car("Merc", "Hyundai", 2045, 2018, 15640f));
        cars.add(new Car("Accent", "Hyundai", 3200, 2017, 15960f));
        cars.add(new Car("Accent", "Hyundai", 4500, 2012, 12000f));

        cars.forEach(car -> {
            car.setDominationCount(cars);
        });

        double averagePrice = cars.stream()
                                    .map(car -> car.getPrice())
                                    .reduce(new CarPriceResultHolder(),
                                            (result, price) -> {
                                                result.accumulate(price);
                                                return result;
                                            },
                                            (finalResult, intermediateResult) -> {
                                                finalResult.combine(intermediateResult);
                                                return finalResult;
                                            })
                                    .getAverage();
        System.out.println(averagePrice);
    }
}