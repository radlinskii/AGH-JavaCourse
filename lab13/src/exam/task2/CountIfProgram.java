package exam.task2;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class CountIfProgram {
    static int[] array;
    static BlockingQueue<Integer> results = new ArrayBlockingQueue<Integer>(101);

    static void initArray(int size){
        array = new int[size];
        Random r = new Random();
        for(int i=0;i<size;i++){
            array[i]= r.nextInt(100);
        }
    }

    static void parallelCountIf(int cnt, IntPredicate pred) throws InterruptedException {

        CountIf threads[]=new CountIf[cnt];
        int block  = array.length / cnt;
        for(int i = 0, start = 0; i < cnt; i++){
            threads[i] = new CountIf(start, start + block, pred);
            start += block;
        }

        double t1 = System.nanoTime()/1e6;

        for(CountIf thread : threads){
            thread.start();
        }
        double t2 = System.nanoTime()/1e6;

        double sum = 0;

        for(int i = 0; i < threads.length; i++){
            sum += results.take();
        }

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f suma=%f <- powinna byc okolo 0.1\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                sum/100000000);
    }



    public static void main(String[] args) throws InterruptedException {

        initArray(100000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64, 100}){
            parallelCountIf(cnt, a -> a < 10);
        }

    }

    static class CountIf extends Thread{

        private final int start;
        private final int end;
        private final IntPredicate pred;


        CountIf(int start, int end, IntPredicate pred){
            this.start = start;
            this.end = end;
            this.pred = pred;
        }

        public void run(){
            IntStream str = Arrays.stream(array).skip(start).limit(end-start).filter(pred);
            try {
                results.put((int)str.count());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
