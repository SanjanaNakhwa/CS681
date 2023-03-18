package edu.umb.CS681.hw01;
import java.util.ArrayList;
import java.util.Comparator;
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

        //Year start
        // Minimum year above threshold
        Car minYearAboveThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getYear() >= 2017 )
                                    .min(Comparator.comparing ((Car car) -> car.getYear() ))
                                    .get();
        System.out.println("Car with MINIMUM YEAR value ABOVE threshold 2017:");
        System.out.println(minYearAboveThreshold.toString());
        System.out.println();                                 


        //Max Year above threshold                         
        Car maxYearAboveThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getYear() >= 2017 )   
                                    .max(Comparator.comparing ((Car car) -> car.getYear() ))
                                    .get();

        System.out.println("Car with MAXIMUM YEAR value ABOVE threshold 2017:");
        System.out.println(maxYearAboveThreshold.toString());
        System.out.println();                                 


        //Average Year above threshold

        int averageYearAboveThreshold = (int) cars.stream()
                                        .filter( (Car car)-> car.getYear() >= 2017 )   
                                        .mapToInt((Car car) -> car.getYear()).average()
                                        .orElse(0);
        
        System.out.println("Average car Year ABOVE threshold of 2017:");                                
        System.out.println(averageYearAboveThreshold);
        System.out.println();                                 

        //Number of cars above threshold
        int countAboveYearThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getYear() >= 2017 ) 
                        .count();
        System.out.println("Count of cars ABOVE threshold of 2017:");                                
        System.out.println(countAboveYearThreshold); 
        System.out.println();                                 




        // Min
        Car minYearBelowThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getYear() < 2017 )
                                    .min(Comparator.comparing ((Car car) -> car.getYear() ))
                                    .get();
        System.out.println("Car with MINIMUM YEAR value BELOW threshold 2017:");
        System.out.println(minYearBelowThreshold.toString());
        System.out.println();                                 


        //max                            
        Car maxYearBelowThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getYear() < 2017 )   
                                    .max(Comparator.comparing ((Car car) -> car.getYear() ))
                                    .get();

        System.out.println("Car with MAXIMUM YEAR value BELOW threshold 2017:");
        System.out.println(maxYearBelowThreshold.toString());
        System.out.println();                                 


        //Average

        int averageYearBelowThreshold = (int) cars.stream()
                                        .filter( (Car car)-> car.getYear() < 2017 )   
                                        .mapToInt((Car car) -> car.getYear()).average()
                                        .orElse(0);
        
        System.out.println("Average car YEAR BELOW threshold of 2017:");                                
        System.out.println(averageYearBelowThreshold);
        System.out.println(); 

        //Number of cars below threshold
        int countBelowYearThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getYear() < 2017 ) 
                        .count();
        System.out.println("Count of cars BELOW threshold of 2017:");                                
        System.out.println(countBelowYearThreshold); 
        System.out.println(); 
        

        //  Year End

        //Price Start

        // Minimum price above threshold
        Car minPriceAboveThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getPrice() >= 40000 )
                                    .min(Comparator.comparing ((Car car) -> car.getPrice() ))
                                    .get();
        System.out.println("Car with MINIMUM PRICE value ABOVE threshold 40000:");
        System.out.println(minPriceAboveThreshold.toString());
        System.out.println();                                 


        //Max Price above threshold                         
        Car maxPriceAboveThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getPrice() >= 40000 )   
                                    .max(Comparator.comparing ((Car car) -> car.getPrice() ))
                                    .get();

        System.out.println("Car with MAXIMUM PRICE value ABOVE threshold 40000:");
        System.out.println(maxPriceAboveThreshold.toString());
        System.out.println();                                 


        //Average price above threshold

        double averagePriceAboveThreshold = cars.stream()
                                        .filter( (Car car)-> car.getPrice() >= 40000 )   
                                        .mapToDouble((Car car) -> car.getPrice()).average()
                                        .orElse(0.0);
        
        System.out.println("Average car price ABOVE threshold of 40000:");                                
        System.out.println(averagePriceAboveThreshold);
        System.out.println();                                 

        //Number of cars above threshold
        int countAboveThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getPrice() >= 40000 ) 
                        .count();
        System.out.println("Count of cars ABOVE threshold of 40000:");                                
        System.out.println(countAboveThreshold); 
        System.out.println();                                 




        // Min
        Car minPriceBelowThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getPrice() < 40000 )
                                    .min(Comparator.comparing ((Car car) -> car.getPrice() ))
                                    .get();
        System.out.println("Car with MINIMUM PRICE value BELOW threshold 40000:");
        System.out.println(minPriceBelowThreshold.toString());
        System.out.println();                                 


        //max                            
        Car maxPriceBelowThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getPrice() < 40000 )   
                                    .max(Comparator.comparing ((Car car) -> car.getPrice() ))
                                    .get();

        System.out.println("Car with MAXIMUM PRICE value BELOW threshold 40000:");
        System.out.println(maxPriceBelowThreshold.toString());
        System.out.println();                                 


        //Average

        double averagePriceBelowThreshold = cars.stream()
                                        .filter( (Car car)-> car.getPrice() < 40000 )   
                                        .mapToDouble((Car car) -> car.getPrice()).average()
                                        .orElse(0.0);
        
        System.out.println("Average car price BELOW threshold of 40000:");                                
        System.out.println(averagePriceBelowThreshold);
        System.out.println(); 

        //Number of cars below threshold
        int countBelowThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getPrice() < 40000 ) 
                        .count();
        System.out.println("Count of cars BELOW threshold of 40000:");                                
        System.out.println(countBelowThreshold); 
        System.out.println(); 


        //Price End


    


        // Mileage Start


        // Minimum mileage above threshold
        Car minMileageAboveThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getMileage() >= 3500 )
                                    .min(Comparator.comparing ((Car car) -> car.getMileage() ))
                                    .get();
        System.out.println("Car with MINIMUM MILEAGE value ABOVE threshold 3500:");
        System.out.println(minMileageAboveThreshold.toString());
        System.out.println();                                 


        //Max Mileage above threshold                         
        Car maxMileageAboveThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getMileage() >= 3500 )   
                                    .max(Comparator.comparing ((Car car) -> car.getMileage() ))
                                    .get();

        System.out.println("Car with MAXIMUM MILEAGE value ABOVE threshold 3500:");
        System.out.println(maxMileageAboveThreshold.toString());
        System.out.println();                                 


        //Average Mileage above threshold

        double averageMileageAboveThreshold = cars.stream()
                                        .filter( (Car car)-> car.getMileage() >= 3500 )   
                                        .mapToDouble((Car car) -> car.getMileage()).average()
                                        .orElse(0.0);
        
        System.out.println("Average car MILEAGE ABOVE threshold of 3500:");                                
        System.out.println(averageMileageAboveThreshold);
        System.out.println();                                 

        //Number of cars above threshold
        int carCountAboveMileageThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getMileage() >= 3500 ) 
                        .count();
        System.out.println("Count of cars ABOVE threshold of 3500:");                                
        System.out.println(carCountAboveMileageThreshold); 
        System.out.println();




        // Minimum mileage below threshold
        Car minMileageBelowThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getMileage() < 3500 )
                                    .min(Comparator.comparing ((Car car) -> car.getMileage() ))
                                    .get();
        System.out.println("Car with MINIMUM MILEAGE value BELOW threshold 3500:");
        System.out.println(minMileageBelowThreshold.toString());
        System.out.println();                                 


        //Max Mileage above threshold                         
        Car maxMileageBelowThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getMileage() < 3500 )   
                                    .max(Comparator.comparing ((Car car) -> car.getMileage() ))
                                    .get();

        System.out.println("Car with MAXIMUM MILEAGE value Below threshold 3500:");
        System.out.println(maxMileageBelowThreshold.toString());
        System.out.println();                                 


        //Average Mileage above threshold

        double averageMileageBelowThreshold = cars.stream()
                                        .filter( (Car car)-> car.getMileage() < 3500 )   
                                        .mapToDouble((Car car) -> car.getMileage()).average()
                                        .orElse(0.0);
        
        System.out.println("Average car MILEAGE BELOW threshold of 3500:");                                
        System.out.println(averageMileageBelowThreshold);
        System.out.println();                                 

        //Number of cars above threshold
        int carCountBelowMileageThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getMileage() < 3500 ) 
                        .count();
        System.out.println("Count of cars BELOW threshold of 3500:");                                
        System.out.println(carCountBelowMileageThreshold); 
        System.out.println();
         

        //Mileage End





        //Domination Count Start
        
        // Minimum Domination Count above threshold
        Car minDCAboveThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getDominationCount() >= 2 )
                                    .min(Comparator.comparing ((Car car) -> car.getDominationCount() ))
                                    .get();
        System.out.println("Car with MINIMUM DOMINATION COUNT value ABOVE threshold 2:");
        System.out.println(minDCAboveThreshold.toString());
        System.out.println();                                 


        //Max DOMINATION COUNT above threshold                         
        Car maxDCAboveThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getDominationCount() >= 2 )   
                                    .max(Comparator.comparing ((Car car) -> car.getDominationCount() ))
                                    .get();

        System.out.println("Car with MAXIMUM DOMINATION COUNT value ABOVE threshold 2:");
        System.out.println(maxDCAboveThreshold.toString());
        System.out.println();                                 


        //Average DOMINATION COUNT above threshold

        double averageDCAboveThreshold = cars.stream()
                                        .filter( (Car car)-> car.getDominationCount() >= 2 )   
                                        .mapToDouble((Car car) -> car.getDominationCount()).average()
                                        .orElse(0.0);
        
        System.out.println("Average car DOMINATION COUNT ABOVE threshold of 2:");                                
        System.out.println(averageDCAboveThreshold);
        System.out.println();                                 

        //Number of cars above threshold
        int carCountAboveDCThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getDominationCount() >= 2 ) 
                        .count();
        System.out.println("Count of cars ABOVE threshold of 2:");                                
        System.out.println(carCountAboveDCThreshold); 
        System.out.println();




        // Minimum DOMINATION COUNT below threshold
        Car minDCBelowThreshold = cars.stream()  
                                    .filter( (Car car)-> car.getDominationCount() < 2 )
                                    .min(Comparator.comparing ((Car car) -> car.getDominationCount() ))
                                    .get();
        System.out.println("Car with MINIMUM DOMINATION COUNT value BELOW threshold 2:");
        System.out.println(minDCBelowThreshold.toString());
        System.out.println();                                 


        //Max DOMINATION COUNT above threshold                         
        Car maxDCBelowThreshold = cars.stream() 
                                     .filter( (Car car)-> car.getDominationCount() < 2 )   
                                    .max(Comparator.comparing ((Car car) -> car.getDominationCount() ))
                                    .get();

        System.out.println("Car with MAXIMUM DOMINATION COUNT value Below threshold 2:");
        System.out.println(maxDCBelowThreshold.toString());
        System.out.println();                                 


        //Average Mileage above threshold

        double averageDCBelowThreshold = cars.stream()
                                        .filter( (Car car)-> car.getDominationCount() < 2 )   
                                        .mapToDouble((Car car) -> car.getDominationCount()).average()
                                        .orElse(0.0);
        
        System.out.println("Average car DOMINATION COUNT BELOW threshold of 2:");                                
        System.out.println(averageDCBelowThreshold);
        System.out.println();                                 

        //Number of cars above threshold
        int carCountBelowDCThreshold = (int) cars.stream()
                        .filter( (Car car)-> car.getDominationCount() < 2 ) 
                        .count();
        System.out.println("Count of cars DOMINATION COUNT threshold of 2:");                                
        System.out.println(carCountBelowDCThreshold); 
        System.out.println();
    }
}