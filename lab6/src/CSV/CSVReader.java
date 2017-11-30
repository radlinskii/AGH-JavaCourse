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
        this(filename, delimeter, true);
    }

    CSVReader(String filename) throws IOException {
        this(filename, ",",true);
    }




    String[]current;
    boolean next(){
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //
        return false;
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

        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength() {
        return 0;
    }
}