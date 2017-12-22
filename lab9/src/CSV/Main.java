package CSV;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.DoubleConsumer;

public class Main {
    public static void main(String[] args) throws CantCenterEmptyBoundingBox {
        AdminUnitList a = new AdminUnitList();
        try {
            a.read("admin-units.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdminUnitList o = a.selectByName("gmina Wielka Wieś", false);
        AdminUnit modlniczka = o.getUnits().get(0).children.get(6);
        AdminUnit modlnica = o.getUnits().get(0).children.get(5);

        for(int i = 0; i < o.getUnits().get(0).children.size(); i++) {
            //System.out.println(o.getUnits().get(0).children.get(i).toString());
            if(o.getUnits().get(0).children.get(i).getName().equals("Modlniczka")){
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
            double t1 = System.nanoTime()/1e6;
            a.getNeighbors(modlniczka, modlniczka, 100);
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n\n",t2-t1);
            for(AdminUnit neighbor : modlniczka.neighbours){
                System.out.println(neighbor.getName() + "oo ");
            }
        } catch (CantCenterEmptyBoundingBox cantCenterEmptyBoundingBox) {
            cantCenterEmptyBoundingBox.printStackTrace();
        }


        PrintStream out = new PrintStream(System.out);
    a.filter(p->p.getName().equals("gmina Jednorożec"))
                .list(out);
        out.println();

        AdminUnitList list = a;

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(el -> el.getParent().getName().contains("Wielka Wie"))
                .sort((el1, el2) -> (el1.getName().compareTo(el2.getName())))
                .limit(3);
        query.execute().list(out);


    }
}
