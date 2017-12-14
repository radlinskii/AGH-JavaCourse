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
            System.out.println(o.getUnits().get(0).children.get(i).toString());
            if(o.getUnits().get(0).children.get(i).getName().equals("Modlniczka")){
                modlniczka = o.getUnits().get(0).children.get(i);
            }
            if (o.getUnits().get(0).children.get(i).getName().equals("Modlnica")) {
                modlnica = o.getUnits().get(0).children.get(i);
            }

            System.out.println(modlniczka.getBox().distanceTo(modlnica.getBox()));
        }


        System.out.println(o.getUnits().get(0).children.get(6).getBox().toLINESTRING());

        try {
            double t1 = System.nanoTime()/1e6;
            o = a.getNeighbors(modlniczka, 4);
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n\n",t2-t1);
            for(AdminUnit neighbor : o.getUnits()){
                System.out.println(neighbor.getName());
            }
        } catch (CantCenterEmptyBoundingBox cantCenterEmptyBoundingBox) {
            cantCenterEmptyBoundingBox.printStackTrace();
        }


        PrintStream out = new PrintStream(System.out);
        a.filter(p->p.getName().startsWith("K")).sortInPlaceByArea().list(out);
        out.println();
        a.filter(p->
                p.getAdminLevel() == 6 &&
                        p.getParent().getName().equals("województwo małopolskie"))
                .list(out);
        out.println();
    }
}
