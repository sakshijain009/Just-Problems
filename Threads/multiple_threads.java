import java.util.concurrent.atomic.AtomicInteger;

public class PrintMillionWithSync {
    private static final int N = 1_000_000;
    private static final int THREADS = 4; // try with 4 worker threads
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                int current = counter.getAndIncrement(); // safely increment
                if (current > N) {
                    break; // stop when limit reached
                }
                System.out.println(current);
            }
        };

        Thread[] workers = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            workers[i] = new Thread(task);
            workers[i].start();
        }

        // wait for all threads to finish
        for (Thread t : workers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
