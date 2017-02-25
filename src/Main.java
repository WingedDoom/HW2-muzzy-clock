import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
	    String timeStr = "11:05";
        System.out.println(parseString(timeStr));
    }

    public static int parseString(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("k:mm");
        LocalTime time = LocalTime.parse(timeString, formatter);
        return time.getMinute() + 60*time.getHour();
    }
}
