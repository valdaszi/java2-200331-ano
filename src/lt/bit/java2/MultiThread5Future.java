package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MultiThread5Future {

    public static void main(String[] args) {

//        Callable<Double> task1 = () -> {
//            Random random = new Random();
//            double d = 0;
//            long max = 100_000_000;
//            for (long i = 0; i < max; i++) {
//                d += random.nextDouble();
//            }
//            return d;
//        };

        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Future<Double>> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            // results.add(executorService.submit(task1));
            results.add(executorService.submit(TestRandom.withMaxLoop((i+1) * 10_000_000)));
        }

        System.out.println("All tasks in queue - " + results.size());

        Future<Double> lastTask = results.get(results.size() - 1);

        System.out.println("[].isDone() ? " + lastTask.isDone());
        System.out.println("[].isCancelled() ? " + lastTask.isCancelled());


        for (int nr = 1; nr < results.size(); nr++) {
            Future<Double> result = results.get(nr);
            try {
                System.out.println(nr + " " + result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[].isDone() ? " + lastTask.isDone());

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
    }
}

class TestRandom implements Callable<Double> {

    final private long maxLoop;

    public static TestRandom withMaxLoop(long maxLoop) {
        return new TestRandom(maxLoop);
    }

    private TestRandom(long maxLoop) {
        this.maxLoop = maxLoop;
    }

    @Override
    public Double call() throws Exception {
        Random random = new Random();
        double d = 0;
        for (long i = 0; i < maxLoop; i++) {
            d += random.nextDouble();
        }
        return d / maxLoop;
    }
}
