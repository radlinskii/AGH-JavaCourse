package CSV;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.DoubleConsumer;

public class Main {
    public static void main(String[] args) throws CantCenterEmptyBoundingBox {
        /*AdminUnitList a = new AdminUnitList();
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

*/

        double arr [] = {2.39,2.44,2.45,2.38,2.43,2.38,2.39,2.43,2.45,2.46};
        /*double arr [] = {1.026,1.032,1.03,1.036,1.039,1.032,1.03,1.027,1.039,1.039};*/
        double arrayLength = arr.length;

        System.out.println(3.80/2.42);
        System.out.println((2.44-2.42)/2.44);
        //System.out.println("U(l) = " + UOdEl);

        double sum = 0;
        for (double anArr : arr) {
            sum += anArr;
        }

        double mean = sum/arrayLength;
        System.out.println("T = " + mean);

        double squareSumDiff = 0;

        for (double anArr : arr) {
            squareSumDiff += Math.pow(anArr - mean,2);
            //System.out.println(String.format( "%.4f", squareSumDiff));
        }

        double niepewnoscT = Math.sqrt(squareSumDiff/(arrayLength*(arrayLength-1)));
        System.out.println("U(T) = " + niepewnoscT);


    }
}
