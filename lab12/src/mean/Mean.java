package mean;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(129);

    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    static void parallelMean(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        int block  = array.length / cnt;
        for(int i = 0, start = 0; i < cnt; i++){
            threads[i] = new MeanCalc(start, start + block);
            start += block;
        }

        double t1 = System.nanoTime()/1e6;

        for(MeanCalc thread : threads){
            thread.start();
        }
        double t2 = System.nanoTime()/1e6;

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

        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64, 128}){
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
        }
    }
}
