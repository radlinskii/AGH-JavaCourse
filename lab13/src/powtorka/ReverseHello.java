package powtorka;

public class ReverseHello extends Thread {
    int id;

    ReverseHello(int _id) {
        id = _id;
    }

    @Override
    public void run() {
        if (id < 10) {
            ReverseHello o = new ReverseHello(id + 1);
            o.run();
            try {
                sleep(500);
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello There " + id);
        }

    }
}
