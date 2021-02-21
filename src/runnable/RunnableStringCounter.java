package runnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**

 mamy plik tekstowy
 dzielimy go na 10 kawałków
 uruchamiamy 10 wątków i każdy z nich znajduje najczęściej pojawiającą się samogłoskę
 @author Dariusz Ceglarek
 @version  1.01
 **/
public class RunnableStringCounter implements  Runnable {
    private static String tekst;
    private String fragment;

    /**
     * Konstruktor klasy RunnableStringCounter
     * @param fragment tekstu
     */
    public RunnableStringCounter(String fragment) {
        this.fragment = fragment;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String plik = "plik.txt";
        czytajPlik(plik);
        System.out.println("Plik ma " + tekst.length() + " znaków");
        int ile = tekst.length();
        int dlugosc = ile/10;
        String[] fragmenty = new String[10];
        Thread[] threads = new Thread[10];
        RunnableStringCounter[] runnables = new RunnableStringCounter[10];
        for(int i = 0; i < 10; i++) {
            if (i != 9)
               fragmenty[i] = tekst.substring(i*dlugosc, i*dlugosc + dlugosc);
            else
                fragmenty[i] = tekst.substring(i*dlugosc);
            runnables[i] = new RunnableStringCounter(fragmenty[i]);
            threads[i] = new Thread(runnables[i]);
            threads[i].start();
        }
    }

    /**
     * Czytanie z pliku tekstowego
     * @param plik - nazwa pliku
     */
    public static void czytajPlik(String plik) {
        File file = new File(plik);
        tekst = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bu = new BufferedReader(fr);
            String linia;
            while((linia = bu.readLine()) != null)
                tekst += linia;
            bu.close();
            fr.close();
        }
        catch(IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Metoda wątku znajduje najczęściej występującą samogłoskę we fragmencie tekstu
     */
    public void run() {
        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("a", 0);
        mapa.put("ą", 0);
        mapa.put("e", 0);
        mapa.put("i", 0);
        mapa.put("o", 0);
        mapa.put("u", 0);
        mapa.put("y", 0);
        for(int i = 0; i < fragment.length(); i++) {
            String znak = fragment.substring(i,i+1);
            if (mapa.containsKey(znak)) {
                int ile = mapa.get(znak) + 1;
                mapa.put(znak, ile);
            }
        }
        String znak = "";
        int max = 0;
        for(Map.Entry<String, Integer> entry : mapa.entrySet()) {
            int ile = entry.getValue();
            if (ile > max) {
                max = ile;
                znak = entry.getKey();
            }
        }
        System.out.println(Thread.currentThread().getName() + " znalazł: " + znak + " (" + max + ")");
    }
}
