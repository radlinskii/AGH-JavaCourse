package mean;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }


    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMean(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        int block  = array.length / cnt;
        for(int i = 0, start = 0; i < cnt; i++){
            threads[i] = new MeanCalc(start, start + block);
            start += block;
        }
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(MeanCalc thread : threads){
            thread.start();
        }
        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''
        for(MeanCalc mc:threads) {
            mc.join();
        }
        // oblicz średnią ze średnich
        double mean, sum = 0;

        for(int i = 0; i < threads.length; i++){
            sum += results.take();
        }
        mean = sum/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }



    public static void main(String[] args) throws InterruptedException {
        //initArray(100000000);
        //MeanCalc meanCalc = new MeanCalc(99999950, 99999999);
        //meanCalc.start();
//        parallelMean(10);
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64}){
            parallelMean(cnt);
        }
    }



    static class MeanCalc extends Thread{

        private final int start;
        private final int end;

        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end = end;
        }
        public void run(){
            // ??? liczymy średnią
            double sum =0;
            for(int i = start;i < end; i++){
                sum += array[i];
            }
            mean = sum/(end-start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }
}
