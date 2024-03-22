import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * File-backed implementation of GameStats
 *
 * Returns the number of games *within the last 30 days* where the person took a given number of guesses
 */
public class StatsFile extends GameStats {
    public static final String FILENAME = "guess-the-number-stats.csv";


    // maps the number of guesses required to the number of games within
    // the past 30 days where the person took that many guesses
    private SortedMap<Integer, Integer> statsMap;

    public StatsFile(CSVReader csvReader, LocalDateTime ldt){
        statsMap = new TreeMap<>();

        try {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                try {
                    LocalDateTime timestamp = LocalDateTime.parse(values[0]);
                    int numGuesses = Integer.parseInt(values[1]);

                    if (timestamp.isAfter(ldt.minusDays(30))) {
                        statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
                    }
                } catch(NumberFormatException nfe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw nfe;
                }
                catch(DateTimeParseException dtpe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw dtpe;
                }
            }
        } catch (CsvValidationException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    public void StatsFileTestDouble(CSVReader csvReader, LocalDateTime ldt, String input){
        statsMap = new TreeMap<>();

        Scanner sc = new Scanner(input);
        sc.useDelimiter("\n");

        try {
            String[] values;
            while ((values = new String[]{sc.next()}) != null) {
                try {
                    LocalDateTime timestamp = LocalDateTime.parse(values[0]);
                    int numGuesses = Integer.parseInt(values[1]);

                    if (timestamp.isAfter(ldt.minusDays(30))) {
                        statsMap.put(numGuesses, 1 + statsMap.getOrDefault(numGuesses, 0));
                    }
                } catch(NumberFormatException nfe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw nfe;
                }
                catch(DateTimeParseException dtpe){
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw dtpe;
                }
            }
        } catch (Exception e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
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


