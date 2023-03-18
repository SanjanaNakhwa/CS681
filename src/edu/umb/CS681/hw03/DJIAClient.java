package edu.umb.CS681.hw03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DJIAClient {

    public static void main(String[] args) {
        try {
            List<List<String>> csv = Files.lines(Paths.get("/Users/sanjananakhwa/Documents/OOPS/CS681Hws/src/edu/umb/CS681/hw03/File.csv"))
                                    .map( line -> {
                                        return Stream.of( line.split(","))
                                                .map( value -> value) 
                                                .collect(Collectors.toList());
                                    } )
                                    .collect(Collectors.toList());

            List<DSummary> summaries = csv.stream()
                                        .skip(1)
                                        .map(row -> {
                                            double open = Double.parseDouble(row.get(1));
                                            double high = Double.parseDouble(row.get(2));
                                            double low = Double.parseDouble(row.get(3));
                                            double close = Double.parseDouble(row.get(4));
                                            return new DSummary(open, close, high, low);
                                        })
                                        .limit(5)
                                        .collect(Collectors.toList());

            DJIAWkSummaryObservable o = new DJIAWkSummaryObservable();
            o.addObserver(new CandleStickObserver());
            summaries.stream().forEach(summary -> o.addSummary(summary));

            DoubleSummaryStatistics highStats = o.getMap().values().stream().mapToDouble(DSummary::getHigh).summaryStatistics();
            DoubleSummaryStatistics lowStats = o.getMap().values().stream().mapToDouble(DSummary::getLow).summaryStatistics();
            double wkOpen = o.getMap().get(1).open;
            double wkClose = o.getMap().get(5).close;
            double wkHigh = highStats.getMax();
            double wkLow = lowStats.getMin();
            o.notifyObservers(new WkSummary(wkOpen, wkClose, wkHigh, wkLow));
            o.getMap().clear();
            
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());       
        }
    }
}
