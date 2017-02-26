import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static int budget;
    private static int[][] timeCostTable;
    private static int result;

    public static void main(String[] args) {
        parseInput();
        System.out.println(getMaxMinutes());
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
            Scanner scanner = new Scanner(new FileReader("input.txt"));
            String[] input = scanner.nextLine().split(" ");
            timeCostTable = new int[input.length/2][2];
            System.out.println("begin parse");
            int i = 0;
            for (String element : input) {
                if (i % 2 == 0) {
                    // should be a time value
                    timeCostTable[i/2][0] = parseTimeString(element);
                } else {
                    // its a cost value
                    timeCostTable[i/2][1] = (int) (Double.parseDouble(element)*100);
                }
                i++;
            }

            budget = (int)(Double.parseDouble(scanner.nextLine())*100);
            System.out.println("ended");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the maximum amount of minutes Muzzy can afford
     * This method uses Knapsack algorithm done with DP
     * @return Maximum amount of minutes Muzzy can afford
     */
    public static int getMaxMinutes() {
        int[][] bufferTable = new int [timeCostTable.length+1][budget+1];

        for(int i = 1; i < bufferTable.length; i++)
            for (int j = 1; j < bufferTable[i].length; j++){
                if (timeCostTable[i-1][1] <= j) {
                    int overflow = j - timeCostTable[i-1][1];
                    int possibleValue = timeCostTable[i-1][0] + bufferTable[i-1][overflow];
                    bufferTable[i][j] = Math.max(bufferTable[i-1][j], possibleValue);
                } else {
                    bufferTable[i][j] = bufferTable[i-1][j];
                }
            }
        return bufferTable[bufferTable.length-1][bufferTable[bufferTable.length-1].length-1];
    }
}
