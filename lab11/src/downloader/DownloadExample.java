package downloader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadExample {

    static AtomicInteger count = new AtomicInteger(0);
    static Semaphore sem = new Semaphore(0);
    static double t9 = 0;
    static double t10 = 0;


    static String [] toDownload = {
        "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
};

    static void sequentialDownload(){
        t9 = System.nanoTime()/1e9;
        for(String url:toDownload){
            new Downloader(url).run();
        }
    }

    static class Downloader implements Runnable{
        private final String url;

        Downloader(String url){
            this.url = url;
        }

        public void run(){

                String fileName = url.substring(url.lastIndexOf("/")+1);//nazwa pliku z url

                try(InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName) ){
                    for(;;){
                        int sign = in.read();
                        if(sign < 0) break;
                        out.write(sign);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Done:"+fileName);
            count.getAndIncrement();
            sem.release();

        }

    }

    static void concurrentDownload(){

        t10 = System.nanoTime()/1e9;
        System.out.printf(Locale.US,"czas sequential : %f\n",t10-t9);
        t9 = System.nanoTime()/1e9;
        count.set(0);
        for(String url:toDownload){
            new Thread(new Downloader(url)).start();
        }

    }

    static void concurrentDownload2(){
        while(count.get() != toDownload.length){
            Thread.yield();
        }
        t10 = System.nanoTime()/1e9;
        System.out.printf(Locale.US,"czas concurrent1 : %f\n",t10-t9);

        t9 = System.nanoTime()/1e9;
        for(String url:toDownload){
            Thread thread = new Thread(new Downloader(url));
            thread.start();
        }

    }

    static void concurrentDownload3() throws InterruptedException {
        sem.acquire(3*toDownload.length);
        t10 = System.nanoTime()/1e9;
        System.out.printf(Locale.US,"czas concurrent2 : %f\n",t10-t9);
        t9 = System.nanoTime()/1e9;
        for(String url:toDownload) {
            new Thread(new Downloader(url)).start();
        }
    }

    static void showConcurrent3Time() throws InterruptedException {
        sem.acquire(toDownload.length);
        t10 = System.nanoTime()/1e9;
        System.out.printf(Locale.US,"czas concurrent3 : %f\n",t10-t9);
    }


    public static void main(String[] args) throws InterruptedException {
        DownloadExample.sequentialDownload();
        DownloadExample.concurrentDownload();
        DownloadExample.concurrentDownload2();
        DownloadExample.concurrentDownload3();
        DownloadExample.showConcurrent3Time();
    }
}
