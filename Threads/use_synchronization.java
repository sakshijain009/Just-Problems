class Main {

    /*
        By making the increment method synchronized,
        you ensure thread safety and prevent race conditions.

        Synchronization ensures that only one thread can
        execute the critical section at a time.

        The program will now correctly output 2200 as the
        final value of count.

     */
    
    static class Counter {
        int count;

        synchronized void increment() {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable obj1 = () -> {
            for (int i = 0; i < 1100; i++) {
                System.out.println("Incrementing counter by T1");
                counter.increment();
            }
        };
        Runnable obj2 = () -> {
            for (int i = 0; i < 1100; i++) {
                System.out.println("Incrementing counter by T2");
                counter.increment();
            }
        };

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.count);
    }
}
