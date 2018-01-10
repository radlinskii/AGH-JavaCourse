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

        double arr [] = {34.8, 33.9, 34.0, 33.9, 33.7, 34.8, 34.2, 34.8, 34.8, 34.8};
        /*double arr [] = {1.026,1.032,1.03,1.036,1.039,1.032,1.03,1.027,1.039,1.039};*/
        double arrayLength = arr.length;
        double pi = 3.14;
        double l = 0.55;
        double UOdEl = 0.00058;

        //System.out.println("l = " + l);
        //System.out.println("U(l) = " + UOdEl);

        double sum = 0;
        for (double anArr : arr) {
            sum += anArr;
        }


        System.out.println((34.68+ 13.78));
        //for (double anArr : arr) {
        //    System.out.print(anArr * 30 + " ");
        //}
        System.out.println(" ");

        double mean = sum/arrayLength;
        System.out.println("T = " + mean);

        double squareSumDiff = 0;

        for (double anArr : arr) {
            squareSumDiff += Math.pow(anArr - mean,2);
            //System.out.println(String.format( "%.4f", squareSumDiff));
        }

        double niepewnoscT = Math.sqrt(squareSumDiff/(arrayLength*(arrayLength-1)));
        System.out.println("U(T) = " + niepewnoscT);

/*

        double UcOdG = Math.sqrt(
                (64*Math.pow(pi,4)*Math.pow(l,2))*(Math.pow(niepewnoscT,2))/Math.pow(mean,6) + 16*Math.pow(pi,4)*Math.pow(UOdEl,2)/Math.pow(mean,2)
        );

        System.out.println(
                "Uc(g) = " + UcOdG
        );

        double g = 4*Math.pow(pi,2)*l/Math.pow(mean,2);
        System.out.println("g = " + g);
        double bladBzwgzldny = Math.abs(9.811-g);
        System.out.println("blad bezwzgledny = " + bladBzwgzldny);
        System.out.println("blad wzgledny = " + String.format( "%.2f", bladBzwgzldny/g*100) + "%");
        boolean o = 2*UcOdG > bladBzwgzldny;
        System.out.println("Czy blad bezwzgledny jest mniejszy od U(g) [Uc(g)*2] ?? " + o);

        List<Double> arr2 = new ArrayList<>();
        int i =0;
        for(double el : arr){
            arr2.add(Math.pow(el,2));
            //System.out.println(arr2.get(i));
            i++;
        }

        double a = 1.54*Math.pow(10,-5);
        pi = 3.14;
        double dKwadrat = Math.pow(0.00071,2);
        l = 1.063;
        double E0 = 200000000000.0;
        //dKwadrat=(4*l)/(a*E0*pi);
        double E = (4*l)/(3.14*a*dKwadrat);
        System.out.println(E0 + "\n" + E + "\n" + l + "\n" + Math.sqrt(dKwadrat));
        System.out.println(Math.abs(E0-E)/E0*100 + " %");
        //9.68*10^(-4)

        a = 1.52*Math.pow(10,-5);
        dKwadrat=Math.pow(0.00118,2);
        pi = 3.14;
        //double dKwadrat = Math.pow(0.00066,2);
        l = 1.063;
        E0 = 95000000000.0;
        //dKwadrat=(4*l)/(a*E0*pi);
        E = (4*l)/(3.14*a*dKwadrat);
        System.out.println("\n mosiadz \n" + E0 + "\n" + E + "\n" + l + "\n" + Math.sqrt(dKwadrat));
        System.out.println(Math.abs(E0-E)/E0*100 + " %");

        System.out.println(
                Math.sqrt(
                        Math.pow(0.00058/1.063,2) +
                        Math.pow(2*0.0000058/0.00071,2) +
                        Math.pow(2.18*Math.pow(10,-7)/(1.54*Math.pow(10,-5)),2)
                )
        );

        System.out.println(
                Math.sqrt(
                                Math.pow(0.00058/1.063,2) +
                                Math.pow(2*0.0000058/0.00118,2) +
                                Math.pow(6.9*Math.pow(10,-7)/(1.56*Math.pow(10,-5)),2)
                )
        );

*/


    }
}
