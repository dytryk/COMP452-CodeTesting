import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This is the test with dependency injection.  It also doesn't work and I can't figure out why for the life of me
public class StatsFileTest {

    @Test
    void testStatsFileWithValidData() {
        // Mock CSV data
        String csvData = "2020-02-24T20:15:28.750793100,3\n" +
        "2020-02-24T20:56:09.088002200,9\n" +
        "2020-02-24T21:02:13.347392500,1\n" +
        "2020-02-24T21:06:40.860330300,10\n" +
        "2020-02-24T21:35:52.413820800,13\n" +
        "2020-02-24T21:38:37.771960700,7\n" +
        "2020-02-24T21:40:31.854307700,9\n";

        StringReader stringReader = new StringReader(csvData);
        CSVReader csvReader = new CSVReader(stringReader);
        LocalDateTime currentTime = LocalDateTime.parse("2024-03-27T10:00:00");
        StatsFileTestDouble statsFile = new StatsFileTestDouble(csvReader, currentTime, csvData);
        SortedMap<Integer, Integer> statsMap = statsFile.getStatsMap();

        assertEquals(1, statsMap.get(1));
        assertEquals(0, statsMap.get(2));
        assertEquals(0, statsMap.get(3));
        assertEquals(0, statsMap.get(4));
        assertEquals(0, statsMap.get(5));
        assertEquals(0, statsMap.get(6));
        assertEquals(1, statsMap.get(7));
        assertEquals(0, statsMap.get(8));
        assertEquals(2, statsMap.get(9));
        assertEquals(1, statsMap.get(10));
        assertEquals(0, statsMap.get(11));
        assertEquals(0, statsMap.get(12));
        assertEquals(1, statsMap.get(13));
    }
}