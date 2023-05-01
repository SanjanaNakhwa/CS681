
package edu.umb.CS681.hw05;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.catalog.Catalog;

public class CensusDataMultipleThreads {
     public static void main(String[] args) {
        
        try {
            List<List<String>> csv = Files.lines(Paths.get("/Users/sanjananakhwa/Documents/OOPS/CS681Hws/src/edu/umb/CS681/hw04/census_data.csv"))
            .map( line -> {
                return Stream.of( line.split(","))
                        .map( value -> value)
                        .collect(Collectors.toList());
            } )
            .collect(Collectors.toList());
            
            // Skip the first line, which contains the column headers
            List<List<String>> lines = csv.stream()
                                          .skip(1)
                                          .collect(Collectors.toList());
                                         
             
            Map<String, Object> allDataProcessing = Collections.synchronizedMap(new HashMap<>());

            //Data processing #1: Identify the areas/blocks next to Charles river and compute the highest, lowest, and average price of those houses.
            
        
            Thread processDataThread1 = new Thread(() -> {
                List<List<String>> chasBlocks = lines.stream()
                                          .filter(line -> line.get(3).contains("1"))                                          
                                          .collect(Collectors.toList());
                                        
             double chasAvgPrice = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).average().orElse(0);
             double chasMinPrice = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).min().orElse(0);
             double chasMaxPrice = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).max().orElse(0);

            
             allDataProcessing.put("chasAvgPrice", chasAvgPrice);
             allDataProcessing.put("chasMinPrice", chasMinPrice);
             allDataProcessing.put("chasMaxPrice", chasMaxPrice);

            
            });

            processDataThread1.start();

            

             // Data processing #2: Identify the areas/blocks within the top (lowest) 10% of "low" crime rate and the top (lowest) 10% of pupil-teacher ratio and compute the max, min, and average of price, NOX concentration, and # of rooms
            

            Thread processDataThread2 = new Thread(() -> {

                int numBlocks = lines.size();
                int top10Percent = (int) Math.round(numBlocks * 0.1);
            List<List<String>> lowCrimeBlocks = lines.stream()
                                                .sorted(Comparator.comparingDouble(block -> Double.parseDouble(block.get(0))))
                                                .limit(top10Percent)
                                                .collect(Collectors.toList());



            List<List<String>> lowPtratioBlocks = lines.stream()
                                                    .sorted(Comparator.comparingDouble(block -> Double.parseDouble(block.get(10))))
                                                    .limit(top10Percent).collect(Collectors.toList());

            List<List<String>> commonBlocks = lowCrimeBlocks.stream()
                                                        .filter(lowPtratioBlocks::contains)
                                                        .collect(Collectors.toList());

            double commonAvgPrice = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).average().orElse(0);
            double commonMinPrice = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).min().orElse(0);
            double commonMaxPrice = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).max().orElse(0);

           
            allDataProcessing.put("commonAvgPrice", commonAvgPrice);
            allDataProcessing.put("commonMinPrice", commonMinPrice);
            allDataProcessing.put("commonMaxPrice", commonMaxPrice);


            double commonAvgNox = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(4))).average().orElse(0);
            double commonMinNox = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(4))).min().orElse(0);
            double commonMaxNox = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(4))).max().orElse(0);

        
            allDataProcessing.put("commonAvgNox", commonAvgNox);
            allDataProcessing.put("commonMinNox", commonMinNox);
            allDataProcessing.put("commonMaxNox", commonMaxNox);

            
            double commonAvgRooms = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(5))).average().orElse(0);
            double commonMinRooms = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(5))).min().orElse(0);
            double commonMaxRooms = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(5))).max().orElse(0);

   
            allDataProcessing.put("commonAvgRooms", commonAvgRooms);
            allDataProcessing.put("commonMinRooms", commonMinRooms);
            allDataProcessing.put("commonMaxRooms", commonMaxRooms);
            

            });

            processDataThread2.start();


             //Data Processing #3: Identify and compute the Average, Min and Max House Age next to Charles River

             Thread processDataThread3 = new Thread(() -> {

            List<List<String>> chasBlocks = lines.stream()
                                            .filter(line -> line.get(3).contains("1"))                                          
                                            .collect(Collectors.toList());
             double chasAvgAge = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(6))).average().orElse(0);
             double chasMinAge = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(6))).min().orElse(0);
             double chasMaxAge = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(6))).max().orElse(0);
 
 

             allDataProcessing.put("chasAvgAge", chasAvgAge);
             allDataProcessing.put("chasMinAge", chasMinAge);
             allDataProcessing.put("chasMaxAge", chasMaxAge);
             });

            processDataThread3.start();


             // Data Processing #4 : Identify and compute the Average, Min and Max Tax rate, CommonBlock of Lowest Crime Rate and PT Ratio
             Thread processDataThread4 = new Thread(() -> {

                int numBlocks = lines.size();
                int top10Percent = (int) Math.round(numBlocks * 0.1);

                List<List<String>> lowCrimeBlocks = lines.stream()
                                                .sorted(Comparator.comparingDouble(block -> Double.parseDouble(block.get(0))))
                                                .limit(top10Percent)
                                                .collect(Collectors.toList());

                List<List<String>> lowPtratioBlocks = lines.stream()
                                                .sorted(Comparator.comparingDouble(block -> Double.parseDouble(block.get(10))))
                                                .limit(top10Percent).collect(Collectors.toList());

                List<List<String>> commonBlocks = lowCrimeBlocks.stream()
                                                        .filter(lowPtratioBlocks::contains)
                                                        .collect(Collectors.toList());

             double commonAvgTaxRatio = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(9))).average().orElse(0);
             double commonMinTaxRatio = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(9))).min().orElse(0);
             double commonMaxTaxRatio = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(9))).max().orElse(0);
 
          
             allDataProcessing.put("commonAvgTaxRatio", commonAvgTaxRatio);
             allDataProcessing.put("commonMinTaxRatio", commonMinTaxRatio);
             allDataProcessing.put("commonMaxTaxRatio", commonMaxTaxRatio);
         
             });

            processDataThread4.start();
            processDataThread1.join();
            processDataThread2.join();
            processDataThread3.join();
            processDataThread4.join();

            //1
            System.out.println("Data processing #1:");
            System.out.println("Average price: " + allDataProcessing.get("chasAvgPrice"));
            System.out.println("Minimum price: " + allDataProcessing.get("chasMinPrice"));
            System.out.println("Maximum price: " + allDataProcessing.get("chasMaxPrice"));

            //2a
            System.out.println("Data processing #2a:");
            System.out.println("Average price: " + allDataProcessing.get("commonAvgPrice"));
            System.out.println("Minimum price: " + allDataProcessing.get("commonMinPrice"));
            System.out.println("Maximum price: " + allDataProcessing.get("commonMaxPrice"));

            //2b
            System.out.println("Data processing #2b:");
            System.out.println("Average price: " + allDataProcessing.get("commonAvgNox"));
            System.out.println("Minimum price: " + allDataProcessing.get("commonMinNox"));
            System.out.println("Maximum price: " + allDataProcessing.get("commonMaxNox"));

            //2c
            System.out.println("Data processing #2c:");
            System.out.println("Average price: " + allDataProcessing.get("commonAvgRooms"));
            System.out.println("Minimum price: " + allDataProcessing.get("commonMinRooms"));
            System.out.println("Maximum price: " + allDataProcessing.get("commonMaxRooms"));

            //3
            System.out.println("Data processing #3:");
            System.out.println("Average price: " + allDataProcessing.get("chasAvgAge"));
            System.out.println("Minimum price: " + allDataProcessing.get("chasMinAge"));
            System.out.println("Maximum price: " + allDataProcessing.get("chasMaxAge"));
 

            //4
            System.out.println("Data processing #4:");
            System.out.println("Average price: " + allDataProcessing.get("commonAvgTaxRatio"));
            System.out.println("Minimum price: " + allDataProcessing.get("commonMinTaxRatio"));
            System.out.println("Maximum price: " + allDataProcessing.get("commonMaxTaxRatio"));

        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());       

        }
    }
}