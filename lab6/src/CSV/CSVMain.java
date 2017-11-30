package CSV;

import java.io.IOException;

public class CSVMain {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("titanic-part.csv",",",true);
        while(reader.next()){
            int id = reader.getInt("PassengerId");
            String name = reader.get("Name");
            double fare = reader.getDouble("Fare");

            System.out.printf("%d %s %f",id, name, fare);
        }


        CSVReader reader2 = new CSVReader("titanic-part.csv",",",true);
        while(reader2.next()){
            int id = reader2.getInt(0);
            String name = reader2.get(3);
            double fare = reader2.getDouble(9);
            System.out.printf("%d %s %f",id, name, fare);

        }

    }

}
