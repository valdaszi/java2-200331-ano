package lt.bit.java2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MultiThread4Executors {

    public static void main(String[] args) {

        long startTimer = System.nanoTime();

        Runnable task1 = () -> {
            Random random = new Random();
            double d = 0;
            long max = 100_000_000;
            for (long i = 0; i < max; i++) {
                d += random.nextDouble();
            }
            System.out.println(d / max);
        };

//        for (int i = 0; i < 10; i++) {
//            new Thread(task1).start();
//        }

        System.out.println("CPUs: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Max memory: " + Runtime.getRuntime().maxMemory());
        System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());


        // newSingleThreadExecutor - vienas thread'as - visi taskai vykdomi nuosekliai
        //      t.y. vienas po kito
        // newFixedThreadPool - fiksuotu thread'u skaiciaus executorius
        // newCachedThreadPool - baigtas threadas nenaikinamas, o grazinamas i pool'a ir
        //      panaudojamas kitam taskui vykdyti
        // newWorkStealingPool = panaudojami visi branduoliai
        ExecutorService executorService = Executors.newScheduledThreadPool(1);


        for (int i = 0; i < 5; i++) {
            executorService.submit(task1);
        }
        executorService.execute(() -> {
            int i = 1;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println(i++ + " :)");
            }
        });


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks in queue - shutdown");
        // sustabdo executoriu nuo nauju tasku priemimo
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();  // sustabdyti veikianti taska
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Nepavyko sustabdyti");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        long timer = (System.nanoTime() - startTimer) / 1000000;
        System.out.println("Laikas: " + timer + "ms");

    }

}




