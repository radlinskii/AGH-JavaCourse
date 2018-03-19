package CSV;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.DoubleConsumer;

public class Main {
    public static void main(String[] args) throws CantCenterEmptyBoundingBox {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdminUnitList e = a.selectByName("powiat pszczyński", false);
        AdminUnitList u = a.selectByName("powiat oświęcimski", false);
        AdminUnit pszczyna = e.getUnits().get(0).children.get(0);
        AdminUnit brzeszcze = u.getUnits().get(0).children.get(0);

        for (int i = 0; i < e.getUnits().get(0).children.size(); i++) {
            if (e.getUnits().get(0).children.get(i).getName().equals("gmina Pszczyna")) {
                pszczyna = e.getUnits().get(0).children.get(i);
            }
            if (e.getUnits().get(0).children.get(i).getName().equals("gmina Brzeszcze")) {
                brzeszcze = e.getUnits().get(0).children.get(i);
            }

        }

        a.getNeighbors(pszczyna, pszczyna, 9);

        System.out.println("aAAAAAAA\n" + pszczyna.getNeighbours());

        AdminUnitList o = a.selectByName("gmina Wielka Wieś", false);
        AdminUnit modlniczka = o.getUnits().get(0).children.get(6);
        AdminUnit modlnica = o.getUnits().get(0).children.get(5);

        for (int i = 0; i < o.getUnits().get(0).children.size(); i++) {
            //System.out.println(o.getUnits().get(0).children.get(i).toString());
            if (o.getUnits().get(0).children.get(i).getName().equals("Modlniczka")) {
                modlniczka = o.getUnits().get(0).children.get(i);
            }
            if (o.getUnits().get(0).children.get(i).getName().equals("Modlnica")) {
                modlnica = o.getUnits().get(0).children.get(i);
            }

            System.out.println(modlniczka.getBox().distanceTo(modlnica.getBox()));
        }


        System.out.println(o.getUnits().get(0).children.get(6).getBox().toLINESTRING());
        AdminUnitList nowa = new AdminUnitList();
        try {
            double t1 = System.nanoTime() / 1e6;
            a.getNeighbors(modlniczka, modlniczka, 100);
            double t2 = System.nanoTime() / 1e6;
            System.out.printf(Locale.US, "t2-t1=%f\n\n", t2 - t1);
            for (AdminUnit neighbor : modlniczka.neighbours) {
                System.out.println(neighbor.getName() + "oo ");
            }
        } catch (CantCenterEmptyBoundingBox cantCenterEmptyBoundingBox) {
            cantCenterEmptyBoundingBox.printStackTrace();
        }


        PrintStream out2 = new PrintStream(System.out);
        a.filter(p -> p.getName().equals("gmina Jednorożec"))
                .list(out2);
        out2.println();

        AdminUnitList list = a;

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(el -> el.getParent().getName().contains("Wielka Wie"))
                .sort((el1, el2) -> (el1.getName().compareTo(el2.getName())))
                .limit(5);
        query.execute().list(out2);


        System.out.println();
        String array[] = {"za", "eaa", "X", "aaaa"};
        Arrays.asList(array).sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        Arrays.stream(array).forEach(i -> System.out.println(i));

        System.out.println();
        Arrays.asList(array).sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
        Arrays.stream(array).forEach(i -> System.out.println(i));

        System.out.println();
        Arrays.asList(array).sort((s1, s2) -> Integer.compare(s1.toLowerCase().charAt(0), s2.toLowerCase().charAt(0)));
        Arrays.stream(array).forEach(i -> System.out.println(i));

    }
}
