package CSV;

import java.io.*;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)
            parseHeader();
    }

    CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }
    //...

    CSVReader(String filename, String delimeter) throws IOException {
        this(filename, delimeter, false);
    }

    CSVReader(String filename) throws IOException {
        this(filename, ",");
    }




    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for(int i = 0; i < header.length; i++){
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }

    }

    String[]current;
    boolean next(){
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //
        return false;
    }

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