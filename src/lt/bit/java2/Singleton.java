package lt.bit.java2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Singleton {

    static private Singleton instance = null;

    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("No no");
        }
    }

    static public Singleton getInstance() {
        if (instance == null) {
            // dar kazka turiu padaryti pries
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
            // dar kazka turiu padaryti po
        }
        return instance;
    }

    /*
    ....
     */

    private AtomicInteger counter = new AtomicInteger(1);

    public void print() {
        System.out.println("Kuku " + counter.getAndIncrement());
    }

}


class A1 {

    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        s.print();
        s.print();
        // Singleton s2 = new Singleton();
    }
}

class A2 {
    public void m2() {
        Singleton s = Singleton.getInstance();
    }
}

class LongNotImportantThread extends Thread {

    @Override
    public void run() {
        Random random = new Random();
        for (long i = 0; i < 1_000_000_000; i++) {
            // TODO ...
            double d = random.nextDouble();
            // TODO ...
            Thread.yield();
        }
    }
}

class JavaVolatileDemo {

    volatile static int number;      // = 0
    static boolean ready;   // = false

    public static void main(String[] args) {

        new Thread(() -> {
            // ...
            while (!ready) {
                // Thread.yield();
            }
            System.out.println(number);
        }).start();

        number = 42;
        ready = true;


    }
}

class X implements Runnable {
    static private final ReentrantLock lock = new ReentrantLock();
    // ...

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new X()).start();
        }
    }


    public void run() {
        lock.lock();  // block until condition holds
        // ...
        try {
            // ... method body
            System.out.println(Thread.currentThread().getName() + " working...");
            lock.lock();

        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " The End.");
        lock.unlock();
    }
}

