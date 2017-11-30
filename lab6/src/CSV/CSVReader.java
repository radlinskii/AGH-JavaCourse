package CSV;

import java.io.*;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename,String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",",true);
    }

    boolean isMissing(int columnIndex){
        return !columnLabelsToInt.containsValue(columnIndex);
    }

    boolean isMissing(String column){
        return !columnLabelsToInt.containsKey(column);
    }

    public int getInt(String element) throws EmptyFieldException {
        if(isMissing(element))
            throw(new EmptyFieldException());
        return Integer.parseInt(current[columnLabelsToInt.get(element)]);
    }

    public String get(String element){
        return isMissing(element) ? "" :  current[columnLabelsToInt.get(element)];
    }

    public double getDouble(String element) throws EmptyFieldException {
        if(isMissing(element))
            throw(new EmptyFieldException());
        return Double.parseDouble(current[columnLabelsToInt.get(element)]);
    }

    long getLong(String element) throws EmptyFieldException {
        if(isMissing(element))
            throw(new EmptyFieldException());
        return Long.parseLong(current[columnLabelsToInt.get(element)]);
    }

    long getLong(int index) throws EmptyFieldException {
        if(isMissing(index))
            throw(new EmptyFieldException());
        return Long.parseLong(current[index]);
    }

    public int getInt(int index) throws EmptyFieldException {
        if(isMissing(index))
            throw(new EmptyFieldException());
        return Integer.parseInt(current[index]);
    }

    public String get(int index) {
        return isMissing(index) ? "" : current[index];
    }

    public double getDouble(int index) throws EmptyFieldException {
        if(isMissing(index))
            throw(new EmptyFieldException());
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
                columnLabelsToInt.put(current[i],i);
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
            columnLabelsToInt.put(header[i],i);
        }
    }

    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("accelerator.csv",";",true);
        reader.columnLabelsToInt.get(1);
        System.out.println(reader.columnLabelsToInt.get(7));
        while(reader.next()){
            System.out.println("o");
            int id = 0;
            double fare = 0.0;
            try {
                //id = reader.getInt(0);
                fare = reader.getDouble(2);
            } catch (EmptyFieldException e) {
                e.printStackTrace();
            }
            String name = reader.get(6);
            System.out.printf(Locale.US,"%d %s %f",id, name, fare);
        }
    }
}