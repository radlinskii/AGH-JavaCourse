package CSV;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintStream out = new PrintStream(System.out);

        a.list(out,1,1);

        out.println();

        AdminUnitList o = a.selectByName("gmina Wielka Wieś", false);

        for(int i = 0; i < o.getUnits().get(0).children.size(); i++)
            System.out.println(o.getUnits().get(0).children.get(i));
    }
}
