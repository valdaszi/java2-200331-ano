package lt.bit.java2;

import java.util.*;

public class MultiThread3Blogis2 {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> listas = Collections.synchronizedList(new ArrayList<>());
        Set<Integer> setas = Collections.synchronizedSet(new HashSet<>());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                listas.add(i);
                setas.add(i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                listas.add(i);
                setas.add(i);
            }
        });
        t2.start();

        t1.join();
        t2.join();

        System.out.println(listas.size());
        System.out.println(setas.size());
    }

    synchronized static void a() {
        //TODO ...
    }
}
