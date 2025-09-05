import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == LIMIT) {
            wait(); // wait until space available
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notify(); // notify consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // wait until item available
        }
        int val = queue.poll();
        System.out.println("Consumed: " + val);
        notify(); // notify producer
    }
}

public class ThreadCommunicationDemo {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try { buffer.produce(i); Thread.sleep(100); } 
                catch (InterruptedException e) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try { buffer.consume(); Thread.sleep(200); } 
                catch (InterruptedException e) {}
            }
        });

        producer.start();
        consumer.start();
    }
}
