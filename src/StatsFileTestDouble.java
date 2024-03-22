import com.opencsv.CSVReader;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
This is a test double class for the StatsFile class.
 */

public class StatsFileTestDouble extends GameStats {
    private SortedMap<Integer, Integer> statsMap;

    public StatsFileTestDouble(CSVReader csvReader, LocalDateTime ldt, String input){
        statsMap = new TreeMap<>();

        Scanner sc = new Scanner(input);
        sc.useDelimiter("\n");

        try {
            while (sc.hasNext()) {
                String line = sc.next();
                String[] values = line.split(",");

                LocalDateTime timestamp = LocalDateTime.parse(values[0]);
                int numGuesses = Integer.parseInt(values[1]);

                if (timestamp.isAfter(ldt.minusDays(30))) {
                    statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    @Override
    public int numGames(int numGuesses) {
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses(){
        return (statsMap.isEmpty() ? 0 : statsMap.lastKey());
    }

    public SortedMap<Integer, Integer> getStatsMap() {
        return statsMap;
    }
}
