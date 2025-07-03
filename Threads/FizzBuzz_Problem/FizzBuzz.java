import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private int current = 1;

    Semaphore semFizz = new Semaphore(0);
    Semaphore semBuzz = new Semaphore(0);
    Semaphore semFizzBuzz = new Semaphore(0);
    Semaphore semNumber = new Semaphore(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            semFizz.acquire();
            if (current > n) break;
            printFizz.run();
            semNumber.release();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            semBuzz.acquire();
            if (current > n) break;
            printBuzz.run();
            semNumber.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            semFizzBuzz.acquire();
            if (current > n) break;
            printFizzBuzz.run();
            semNumber.release();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (current <= n) {
            semNumber.acquire();
            if (current % 3 == 0 && current % 5 == 0) {
                semFizzBuzz.release();
            } else if (current % 3 == 0) {
                semFizz.release();
            } else if (current % 5 == 0) {
                semBuzz.release();
            } else {
                printNumber.accept(current);
                semNumber.release();
            }
            current++;
        }

        // Graceful exit
        semFizzBuzz.release();
        semFizz.release();
        semBuzz.release();
    }

    public static void main(String[] args) {
        FizzBuzz fb = new FizzBuzz(25);

        Thread t1 = new Thread(() -> {
            try {
                fb.fizz(() -> System.out.print("Fizz\n"));
            } catch (InterruptedException ignored) {}
        });

        Thread t2 = new Thread(() -> {
            try {
                fb.buzz(() -> System.out.print("Buzz\n"));
            } catch (InterruptedException ignored) {}
        });

        Thread t3 = new Thread(() -> {
            try {
                fb.fizzbuzz(() -> System.out.print("FizzBuzz\n"));
            } catch (InterruptedException ignored) {}
        });

        Thread t4 = new Thread(() -> {
            try {
                fb.number(x -> System.out.print(x + "\n"));
            } catch (InterruptedException ignored) {}
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
