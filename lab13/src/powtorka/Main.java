package powtorka;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread o =new Thread(new ReverseHello(0));
        o.start();
    }
}
