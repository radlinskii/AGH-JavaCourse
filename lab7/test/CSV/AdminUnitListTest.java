package CSV;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AdminUnitListTest {
    @Test
    public void list() throws Exception {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(os);
            a.list(out,1,1);
            String match = os.toString("UTF8");
            assertEquals("Kolonia Południowa 0.836679 11.0 -1.0 -1.0\n",match);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectByName() throws Exception {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
            AdminUnitList ret = a.selectByName("odlnic", false);
            assertEquals(ret.getUnits().size(), 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}