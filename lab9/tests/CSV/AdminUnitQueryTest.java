package CSV;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.Assert.*;

public class AdminUnitQueryTest {

    @Test
    public void execute() {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }


        PrintStream out = new PrintStream(System.out);

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(a)
                .where(el -> el.getParent().getName().contains("Wielka Wie"))
                .sort((el1, el2) -> (el1.getName().compareTo(el2.getName())))
                .limit(5);
        assertEquals(query.execute().getUnits().size(), 5);
    }
}