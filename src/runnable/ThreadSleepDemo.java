package runnable;

public class ThreadSleepDemo {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        String[] messages = {
                "Lech Poznań wygrał w Legią Warszawa 10:0",
                "Prezydent Biden wygłosił przemówienie",
                "Szczepienia na COVID-19 przyspieszają",
                "Vive Kielce znów wygrywa w Lidze Mistrzów",
                "Temperatury w Polsce niczym w lecie",
                "Nadszedł termin wyborów w Uzbekistanie",
                "John Dawkins wystąpił na Broadwayu"
        };

        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());

            for(String message: messages) {
                System.out.println(message);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        // jakieś dalsze instrukcje w programie
    }
}
