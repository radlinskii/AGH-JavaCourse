package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    private BufferedReader reader;
    private String delimiter;
    private boolean hasHeader;
    private String[] current;

    private List<String> columnLabels = new ArrayList<>();
    private Map<String, Integer> columnLabelsToInt = new HashMap<>();

    private CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }

    private boolean isMissing(int columnIndex) {
        return columnIndex >= current.length || current[columnIndex].equals("");

    }

    private boolean isMissing(String column) {
        int colNum = columnLabelsToInt.get(column);
        return current.length <= colNum || current[colNum].equals("");
    }

    int getInt(String element) {
        if (isMissing(element))
            return -1;
        return Integer.parseInt(current[columnLabelsToInt.get(element)]);
    }

    String get(String element) {
        return isMissing(element) ? "" : current[columnLabelsToInt.get(element)];
    }

    double getDouble(String element) {
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
        String line = reader.readLine();
        if (line == null)
            return false;
        else {
            current = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            return true;
        }

    }

    private void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < header.length; i++) {
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