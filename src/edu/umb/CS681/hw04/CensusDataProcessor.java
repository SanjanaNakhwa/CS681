
package edu.umb.CS681.hw04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.catalog.Catalog;

public class CensusDataProcessor {
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
                                         

            //Data processing #1: Identify the areas/blocks next to Charles river and compute the highest, lowest, and average price of those houses.
            List<List<String>> chasBlocks = lines.stream()
                                            .filter(line -> line.get(3).contains("1"))                                          
                                            .collect(Collectors.toList());
            
             

             double chasAvgPrice = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).average().orElse(0);
             double chasMinPrice = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).min().orElse(0);
             double chasMaxPrice = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(13))).max().orElse(0);

            System.out.println("Data processing #1:");
            System.out.println("Areas/blocks next to Charles river: " + chasBlocks.size());
            System.out.println("Average price: " + chasAvgPrice);
            System.out.println("Minimum price: " + chasMinPrice);
            System.out.println("Maximum price: " + chasMaxPrice);

             // Data processing #2: Identify the areas/blocks within the top (lowest) 10% of "low" crime rate and the top (lowest) 10% of pupil-teacher ratio and compute the max, min, and average of price, NOX concentration, and # of rooms
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

            System.out.println("Data processing #2a:");
            System.out.println("Common Blocks with lowest crime and lowest PT ratio " + commonBlocks.size());
            System.out.println("Average price: " + commonAvgPrice);
            System.out.println("Minimum price: " + commonMinPrice);
            System.out.println("Maximum price: " + commonMaxPrice);

            double commonAvgNox = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(4))).average().orElse(0);
            double commonMinNox = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(4))).min().orElse(0);
            double commonMaxNox = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(4))).max().orElse(0);

            System.out.println("Data processing #2b:");
            System.out.println("Common Blocks with lowest crime and lowest PT ratio " + commonBlocks.size());
            System.out.println("Average NOX: " + commonAvgNox);
            System.out.println("Minimum NOX: " + commonMinNox);
            System.out.println("Maximum NOX: " + commonMaxNox);
            
            double commonAvgRooms = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(5))).average().orElse(0);
            double commonMinRooms = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(5))).min().orElse(0);
            double commonMaxRooms = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(5))).max().orElse(0);

            System.out.println("Data processing #2c:");
            System.out.println("Common Blocks with lowest crime and lowest PT ratio " + commonBlocks.size());
            System.out.println("Average Rooms: " + commonAvgRooms);
            System.out.println("Minimum Rooms: " + commonMinRooms);
            System.out.println("Maximum Rooms: " + commonMaxRooms);


             //Data Processing #3: Identify and compute the Average, Min and Max House Age next to Charles River

             double chasAvgAge = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(6))).average().orElse(0);
             double chasMinAge = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(6))).min().orElse(0);
             double chasMaxAge = chasBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(6))).max().orElse(0);
 
             System.out.println("Data processing #1:");
             System.out.println("Areas/blocks next to Charles river: " + chasBlocks.size());
             System.out.println("Average Age: " + chasAvgAge);
             System.out.println("Minimum Age: " + chasMinAge);
             System.out.println("Maximum Age: " + chasMaxAge);


             // Data Processing #4 : Identify and compute the Average, Min and Max Tax rate, CommonBlock of Lowest Crime Rate and PT Ratio
             double commonAvgTaxRatio = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(9))).average().orElse(0);
             double commonMinTaxRatio = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(9))).min().orElse(0);
             double commonMaxTaxRatio = commonBlocks.stream().mapToDouble(block -> Double.parseDouble(block.get(9))).max().orElse(0);
 
             System.out.println("Data processing #4:");
             System.out.println("Common Blocks with lowest crime and lowest PT ratio " + commonBlocks.size());
             System.out.println("Average Tax Ratio: " + commonAvgTaxRatio);
             System.out.println("Minimum Tax Ratio: " + commonMinTaxRatio);
             System.out.println("Maximum Tax Ratio: " + commonMaxTaxRatio);

        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());       

        }
    }
}