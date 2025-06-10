# Java Multithreading and Concurrency Interview Questions with Detailed Answers

## 1. What is multithreading in Java?

Multithreading is a Java feature that allows concurrent execution of two or more parts of a program for maximum CPU utilization. Each part is called a thread, and each thread runs parallel to others.

## 2. How do you create a thread in Java?

You can create a thread in two ways:

1. By extending the `Thread` class and overriding its `run()` method.
2. By implementing the `Runnable` interface and passing it to a `Thread` object.

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}

Thread t = new MyThread();
t.start();
```

## 3. What are the different states of a thread in Java?

1. **New** – Thread is created but not started.
2. **Runnable** – Thread is ready to run.
3. **Blocked** – Waiting for a monitor lock.
4. **Waiting** – Waiting indefinitely for another thread.
5. **Timed Waiting** – Waiting for a specified time.
6. **Terminated** – Thread has exited.

## 4. Difference between Runnable and Thread in Java?

* `Runnable` is a functional interface representing a task to be executed by a thread.
* `Thread` is a class that represents a thread of execution.
  Using `Runnable` is preferred as it allows extending other classes.

## 5. Purpose of the start() method in the Thread class?

The `start()` method initiates the thread's execution, internally calling the `run()` method.

## 6. What is synchronization in Java?

Synchronization is the mechanism to control access to shared resources by multiple threads to prevent data inconsistency.

## 7. How does synchronized keyword work in Java?

The `synchronized` keyword locks an object or method so that only one thread can access the synchronized code at a time.

## 8. What is a deadlock?

A deadlock occurs when two or more threads are blocked forever, each waiting for the other to release a lock.

## 9. Different ways to achieve thread synchronization in Java?

* `synchronized` methods
* `synchronized` blocks
* `ReentrantLock` from `java.util.concurrent.locks`

## 10. Difference between synchronized method and synchronized block?

* **Synchronized method** locks the entire method.
* **Synchronized block** locks only a section of code.

## 11. How do threads communicate with each other?

Threads communicate using `wait()`, `notify()`, and `notifyAll()` methods on shared objects.

## 12. Purpose of wait(), notify(), and notifyAll()?

* `wait()`: Makes the thread wait and releases the lock.
* `notify()`: Wakes up one waiting thread.
* `notifyAll()`: Wakes up all waiting threads.

## 13. Blocking queue in Java?

A `BlockingQueue` is a thread-safe queue that blocks when trying to dequeue from an empty queue or enqueue into a full one.
Example: `ArrayBlockingQueue`, `LinkedBlockingQueue`

## 14. What is a Condition in Java concurrency?

`Condition` allows threads to wait for certain conditions before proceeding. Used with `Lock` interface.

## 15. What is thread safety and why is it important?

Thread safety means that shared data is accessed by only one thread at a time to avoid inconsistencies.

## 16. What are the ways to achieve thread safety in Java?

* Synchronization
* Volatile variables
* Using thread-safe classes (e.g., `ConcurrentHashMap`)
* Using atomic variables

## 17. What is an atomic operation?

An atomic operation is performed as a single unit of work without any interference from other threads.

## 18. Classes in the java.util.concurrent.atomic package?

* `AtomicInteger`
* `AtomicBoolean`
* `AtomicLong`
* `AtomicReference`

## 19. What is the difference between volatile keyword and Atomic classes?

* `volatile` ensures visibility but not atomicity.
* Atomic classes provide both atomicity and visibility.

## 20. What is the ExecutorService in Java and how is it different from using threads directly?

`ExecutorService` is an interface to manage a pool of threads. It decouples task submission from task execution.

## 21. How do you create an ExecutorService?

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
```

## 22. Difference between execute() and submit() methods in ExecutorService?

* `execute()` returns void.
* `submit()` returns a `Future` object.

## 23. How do you gracefully shut down an ExecutorService?

Use `shutdown()` to reject new tasks and complete ongoing tasks.

## 24. Difference between shutdown() and shutdownNow()?

* `shutdown()`: Waits for running tasks to finish.
* `shutdownNow()`: Attempts to stop all tasks immediately.

## 25. What is a Future in Java and how is it related to ExecutorService?

A `Future` represents the result of an asynchronous computation. Returned by `submit()` method of `ExecutorService`.

## 26. How can you cancel a task that has been submitted to an ExecutorService?

Use `future.cancel(true)`.

## 27. What is a ScheduledExecutorService and how do you use it?

Used to schedule tasks after a delay or periodically.

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.schedule(() -> System.out.println("Delayed task"), 5, TimeUnit.SECONDS);
```

## 28. How do you handle exceptions thrown by tasks submitted to an ExecutorService?

Use `Future.get()` which throws `ExecutionException`.

## 29. Explain the lifecycle of an ExecutorService?

1. **Running** – Accepts new tasks.
2. **Shutting down** – No new tasks, continues running old ones.
3. **Terminated** – All tasks complete.

## 30. What is a thread pool and why is it used?

A thread pool is a pool of reusable threads. It improves performance by reusing existing threads.

## 31. Different types of thread pools provided by the Executors utility class?

* Fixed thread pool
* Cached thread pool
* Single-threaded executor
* Scheduled thread pool

## 32. How do you create a fixed thread pool in Java?

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
```

## 33. How do you create a cached thread pool in Java?

```java
ExecutorService executor = Executors.newCachedThreadPool();
```

## 34. What is a single-thread executor?

An executor with a single worker thread executing one task at a time.

## 35. How do you create a scheduled thread pool?

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
```

## 36. What are the benefits of using a thread pool?

* Reduced resource consumption
* Better response time
* Centralized thread management

## 37. How does the thread pool manage the number of threads in the pool?

Depends on pool configuration: core size, max size, and keep-alive time.

## 38. Difference between a fixed thread pool and a cached thread pool?

* Fixed: Limited number of threads.
* Cached: Unbounded threads reused as needed.

## 39. How does ThreadPoolExecutor work internally?

Manages threads based on corePoolSize, maximumPoolSize, keepAliveTime, and workQueue.

## 40. What are the core and maximum pool sizes in ThreadPoolExecutor?

* corePoolSize: Minimum number of threads to keep.
* maximumPoolSize: Maximum allowed threads.

## 41. What is the keep-alive time in ThreadPoolExecutor and how does it affect thread pool behavior?

Idle threads above corePoolSize are terminated after keepAliveTime.

## 42. Blocking queue and how is it used in ThreadPoolExecutor?

Tasks are stored in a blocking queue when all core threads are busy.

## 43. Different types of blocking queues you can use with ThreadPoolExecutor?

* `ArrayBlockingQueue`
* `LinkedBlockingQueue`
* `SynchronousQueue`

## 44. How would you handle a scenario where you need to perform multiple tasks in parallel?

Use `ExecutorService` with an appropriate thread pool.

## 45. Producer-consumer problem and how can you solve it in Java?

Solved using `BlockingQueue` or `wait/notify` for communication.

## 46. Implement a singleton class in a multithreaded environment.

```java
class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
```

## 47. How do you handle exceptions in threads in Java?

Override `Thread.setUncaughtExceptionHandler()`.

## 48. Daemon thread in Java.

A daemon thread is a low-priority thread that runs in the background.

## 49. How do you create a daemon thread in Java?

```java
Thread t = new Thread(() -> {});
t.setDaemon(true);
t.start();
```

## 50. Benefits of using the ConcurrentHashMap over HashMap in a multithreaded environment?

* Thread-safe
* Higher concurrency
* No need to synchronize entire map
