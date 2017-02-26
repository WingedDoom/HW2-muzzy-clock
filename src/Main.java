import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static int budget;
    private static int[][] timeCostTable;

    public static void main(String[] args) {
	    String timeStr = "11:05";
        System.out.println(parseTimeString(timeStr));
    }

    /**
     * Parses a given timeString (of format hh:mm) and returns number of minutes
     * @param timeString A string of format HH:mm to parse
     * @return Time value of given string in minutes
     */
    public static int parseTimeString(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k:mm");
        LocalTime time = LocalTime.parse(timeString, formatter);
        return time.getMinute() + 60*time.getHour();
    }

    /**
     * Fills the table and budget from "input.txt" file
     */
    public static void parseInput() {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            String[] input = scanner.nextLine().split(" ");
            timeCostTable = new int[input.length/2][2];

            int i = 0;
            for (String element : input) {
                if (i % 2 == 0) {
                    // should be a time value
                    timeCostTable[i][0] = parseTimeString(element);
                } else {
                    // its a cost value
                    timeCostTable[i][1] = (int)Double.parseDouble(element)*100;
                }
            }

            budget = (int)Double.parseDouble(scanner.nextLine())*100;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
