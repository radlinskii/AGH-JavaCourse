package poprawa;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws CantCenterEmptyBoundingBox {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        a.writeRootsHTML();
        //a.writeHTML();

    }



}
