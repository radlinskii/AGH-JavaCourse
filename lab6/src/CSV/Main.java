package CSV;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("moj.csv", ",", true);
        int index = 0;
        while (reader.next() && index < 20) {
            double fare = reader.getDouble("Fare");
            String name = reader.get("Name");
            LocalTime date = reader.getTime("Date", "HH:mm:ss");
            System.out.print(fare + " " + name + " " + date.toString() +  "\n");
            index ++;
        }

    }
}
