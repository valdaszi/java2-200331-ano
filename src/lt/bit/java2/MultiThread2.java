package lt.bit.java2;

public class MultiThread2 {

    public static void main(String[] args) {

        Thread t0 = new Thread(() -> {
            System.out.println("t0 started in " + Thread.currentThread().getName());
            System.out.println("t0 work in " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("t0 work ended in " + Thread.currentThread().getName());
        });
        t0.start();

        Thread t1 = new Thread(() -> {
            System.out.println("t1 started in " + Thread.currentThread().getName());

            System.out.println("Ar t0 veikia? " + t0.isAlive());
            try {
                // t1, t.y. einamasis threadas, laukia kol baigsis t0:
                t0.join();
            } catch (InterruptedException e) {
                System.out.println("t1 pazadintas");
            }

            System.out.println("Ar t0 vis dar veikia? " + t0.isAlive());

            System.out.println("t1 work in " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("t1 work ended in " + Thread.currentThread().getName());
        });
        t1.start();

        // cia mes esame 'main' thread'e:
        // laukiam kol baigsis t1 arba kol praeis 1000ms
        try {
            t1.join(1000);
            System.out.println("t1 ar veikia? " + t1.isAlive());
            System.out.println("t1 busena? " + t1.getState());
            // priverstinai pazadiname t1
            t1.interrupt();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The End.");
    }
}
