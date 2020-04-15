package lt.bit.java2;

public class MultiThread3Blogis {


    public static void main(String[] args) {
        mainThreadOnly();
        multiThread();
    }


    static void counter1(Counter counter) {
        for (long i = 0; i < 1_000_000; i++) {
            counter.inc();
        }
    }

    static void counter2(Counter counter) {
        for (long i = 0; i < 1_000_000; i++) {
            counter.dec();
        }
    }

    static void mainThreadOnly() {
        Counter counter = new Counter();

        long start = System.currentTimeMillis();

        counter1(counter);
        counter2(counter);

        long time = System.currentTimeMillis() - start;
        System.out.println(counter.getCount() + " in " + time + "ms");
    }

    static void multiThread() {
        Counter counter = new Counter();

        long start = System.currentTimeMillis();

        Thread t0 = new Thread(() -> counter1(counter));
        t0.start();

        Thread t1 = new Thread(() -> counter2(counter));
        t1.start();

        // Reikia laukti kol baigsis abu thread'ai
        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
        }

        long time = System.currentTimeMillis() - start;
        System.out.println(counter.getCount() + " in " + time + "ms");
    }
}

class Keys {
    public static Object key1 = new Object(); // "asaasdewtercbvdfgrtyhg";
}

class Counter {
    private long count;

    public void inc() {
        synchronized (this) { // (Keys.key1) { // Counter.class
            this.count++;
        }
    }

    public synchronized void dec() {
        count--;
    }

    public long getCount() {
        return count;
    }
}

class A1111 {} // Keys.key1
