package CSV;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
