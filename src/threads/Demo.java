package threads;

public class Demo extends Thread {
    private int numer;
    public Demo(int numer) {
        this.numer = numer;
    }
    public void run() {
        try {
            System.out.println("Początek wątku " + numer);
            for (int i = 0; i < 4; i++) {
                System.out.println("wątek " + numer + " -> " + i);
                sleep(1);
            }
        }
        catch(InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Demo w1 = new Demo(1);
            Demo w2 = new Demo(2);
            Demo w3 = new Demo(3);
            w1.start();
            System.out.println("tutaj 1");
            w2.start();
            w1.join();
            System.out.println("tutaj 2");
            w3.start();
        }
        catch(InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }
}
