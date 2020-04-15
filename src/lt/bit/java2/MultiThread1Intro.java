package lt.bit.java2;

public class MultiThread1Intro {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        long start = System.currentTimeMillis();

        DaugVeiksmo daugVeiksmo = new DaugVeiksmo();
//        daugVeiksmo.run();

        Thread thread = new Thread(daugVeiksmo, "Kitas threadas");
        // jei setDaemon(false) (default reiksme) - tai "tevas" thredas lauks kol baigisis "vaikas"
        // jei setDaemon(true) - tai "vaikas" thredas bus nuzudytas kai baigsis "tevas" thredas
//        thread.setDaemon(true);
        thread.start();

        // Galima startuoti ir daugiau threadu:
//        new Thread(daugVeiksmo).start();
//        new Thread(daugVeiksmo).start();


        // pries baigiant main threda palaukiam 1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("The End in " + time + "ms");

    }

}

class DaugVeiksmo implements Runnable {

    @Override
    public void run() {
        System.out.println("DaugVeiksmo: " + Thread.currentThread().getName());
        long start = System.currentTimeMillis();

        new Thread(() -> {
            System.out.println("DaugVeiksmo subthread: " + Thread.currentThread().getName());
            long startSub = System.currentTimeMillis();

            // imituokime kad kazka darome kas trunka apie 3 sekundes
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("DaugVeiksmo subthread finished in " + (System.currentTimeMillis() - startSub) + "ms");
        }).start();


        // imituokime kad kazka darome kas trunka apie 2 sekundes
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("DaugVeiksmo finished in " + time + "ms");
    }
}
