package runnable;

import java.util.Random;

public class RunnableExample implements Runnable {
    Thread thread;
    private int number;
    public RunnableExample(int number, Thread thread) {
        this.number = number;
        this.thread = thread;
    }
    public RunnableExample(int number ) {
        this.number = number;
        this.thread = null;
    }
    public static void main(String[] args) {
        try {
            System.out.println("Jestem w : " + Thread.currentThread().getName());

            RunnableExample runnable1 = new RunnableExample(1);
            Thread thread1 = new Thread(runnable1);
            thread1.setName("wątek 1");
            thread1.start();
            Runnable runnable2 = new RunnableExample(2, thread1);
            Thread thread2 = new Thread(runnable2);
            thread2.setName("wątek 2");
            thread2.start();
            thread1.join();
            System.out.println("tutaj będziemy na końcu");
        }
        catch(InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            Random los = new Random();
            System.out.println("Jestem w : " + Thread.currentThread().getName());
            for(int i = 0; i < 5; i++) {
                System.out.println("Liczenie(" + number + "): " + i);
                int ile = los.nextInt(10);
                if (ile > 4)
                    if (thread != null) {
                        thread.join();
                        System.out.println("join dla: " + thread.getName());
                    }
                Thread.sleep(100);
            }
            }
            catch(InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
    }
}