package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }

    boolean isMissing(int columnIndex) {
        return columnIndex >= current.length || current[columnIndex].equals("");

    }

    boolean isMissing(String column) {
        int colNum = columnLabelsToInt.get(column);
        return current.length <= colNum || current[colNum].equals("");
    }

    public int getInt(String element)  {
        if (isMissing(element))
            return -1;
        return Integer.parseInt(current[columnLabelsToInt.get(element)]);
    }

    public String get(String element) {
        return isMissing(element) ? "" : current[columnLabelsToInt.get(element)];
    }

    public double getDouble(String element) {
        if (isMissing(element))
            return -1;
        return Double.parseDouble(current[columnLabelsToInt.get(element)]);
    }

    long getLong(String element) {
        if (isMissing(element))
            return -1;
        return Long.parseLong(current[columnLabelsToInt.get(element)]);
    }

    long getLong(int index) {
        if (isMissing(index))
            return -1;
        return Long.parseLong(current[index]);
    }

    public int getInt(int index) {
        if (isMissing(index))
            return -1;
        return Integer.parseInt(current[index]);
    }

    public String get(int index) {
        return isMissing(index) ? "" : current[index];
    }

    public double getDouble(int index) {
        if (isMissing(index))
            return -1;
        return Double.parseDouble(current[index]);
    }


    boolean next() throws IOException {
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //

        String line = reader.readLine();
        if (line == null)
            return false;
        else {
            current = line.split(delimiter);

            for (int i = 0; i < current.length; i++) {
                System.out.print(" " + current[i]);
            }
            return true;
        }

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
            columnLabelsToInt.put(header[i], i);
        }
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

}