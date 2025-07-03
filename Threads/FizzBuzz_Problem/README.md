# 🧵 FizzBuzz with 4 Threads – Java Multithreading Problem

## ✅ Problem Statement

Implement a multithreaded version of the classic FizzBuzz problem using **four threads**.  
Given an integer `n`, print numbers from `1` to `n` with the following rules:

- Print `"Fizz"` if the number is divisible by **3**
- Print `"Buzz"` if the number is divisible by **5**
- Print `"FizzBuzz"` if the number is divisible by **both 3 and 5**
- Otherwise, print the number itself

However, each rule should be handled by a **separate thread**:

| Thread Name | Role                            |
|-------------|---------------------------------|
| `fizz`      | Prints `"Fizz"` for multiples of 3 only (not 5) |
| `buzz`      | Prints `"Buzz"` for multiples of 5 only (not 3) |
| `fizzbuzz`  | Prints `"FizzBuzz"` for multiples of both 3 and 5 |
| `number`    | Prints the number if it's not divisible by 3 or 5 |

## 🧠 Core Logic & Coordination

To solve this, you must synchronize access to shared resources and ensure that:

1. **Each thread executes only when its condition is met** (e.g., fizz thread only prints when current number is divisible by 3).
2. **The threads print in correct order from 1 to n**.
3. **No race conditions or missing values occur.**

### 👇 Key Concepts to Use:

- Use **semaphores** or **synchronized blocks with `wait()`/`notifyAll()`**.
- Maintain a shared counter `current` to track which number is being evaluated.
- Only one thread should "control" the flow (typically the `number()` thread).
- Other threads wait on their semaphores and print when allowed.
- After printing, a thread must **release control back** to continue the next number.

## 💡 Flow Example for n = 5
- 1 → number thread prints 1
- 2 → number thread prints 2
- 3 → fizz thread prints "Fizz"
- 4 → number thread prints 4
- 5 → buzz thread prints "Buzz"

## 🛠️ What is a Semaphore in Java?

A **Semaphore** in Java is a concurrency utility from `java.util.concurrent` that controls access to shared resources using permits.

### 🔐 Definition:
> A semaphore maintains a set of **permits**. Threads can acquire a permit before proceeding. If no permit is available, the thread waits until one is released.

### 🧭 Why Use Semaphore?

In multithreaded problems like FizzBuzz:

- It ensures that **only the correct thread is allowed to print** for each number.
- Prevents **race conditions** by allowing only one thread to proceed at a time based on logic.
- Helps you simulate **conditional locks** (e.g., only `fizz` should print when `current % 3 == 0`).

### 🔍 What Does the `0` Mean?

The number `0` passed to the `Semaphore` constructor defines the **initial number of available permits**.

A **permit** is like a signal — it tells a thread whether it can proceed or not.

So:

```java
new Semaphore(0);
```

means that no thread can acquire the semaphore right away. Threads calling semFizz.acquire() will block and wait until some other thread releases a permit using semFizz.release().

### Logic in Code
🧠 Key Concept
The use of Runnable lets you inject behavior into a thread without hardcoding what it does. It’s a form of callback — and Runnable is Java’s simplest interface for this.

```java
public void fizz(Runnable printFizz) throws InterruptedException
```

#### ✅ What does this mean?
- fizz is a method that will be executed by the Fizz thread in the FizzBuzz problem.
- It takes one argument, printFizz, which is a Runnable.
- That means: it is a block of code that can be executed via printFizz.run().
- It can throw InterruptedException (required when using Semaphore.acquire()).

#### 🧠 But how does printFizz.run() do System.out.println("Fizz")?
Because the caller of this method (typically the main thread or thread setup code) passes in a Runnable that already defines what to print.


Here’s how it might be passed in:
```java
Thread t1 = new Thread(() -> {
    try {
        fizzBuzz.fizz(() -> System.out.print("Fizz "));
    } catch (InterruptedException ignored) {}
});
```
🔁 So inside fizz():

- When we call printFizz.run();
- It executes the code in the lambda → System.out.print("Fizz ")

### ❓ Doubt: Why Do We Call `.release()` Without `.acquire()`?

In the FizzBuzz multithreading problem, we have the following code in the `number()` method:

```java
if (current % 3 == 0 && current % 5 == 0) {
    semFizzBuzz.release();  // Where is semFizzBuzz.acquire()?
}
```

The doubt is - Why do we call semFizzBuzz.release() without calling semFizzBuzz.acquire() first? Doesn’t every .release() need a matching .acquire()?

**✅ Answer:** It’s About Thread Communication, Not Pairing in Same Method. This is completely valid and actually a key pattern in multithreading.

**🧵 Think in Terms of Multiple Threads:**
The .release() and .acquire() don’t have to appear in the same thread. In fact, they are intentionally used in different threads to signal one another.

#### 👇 How It Works in FizzBuzz
1. fizzbuzz() thread is waiting:
```java
semFizzBuzz.acquire();  // Blocks until someone calls release
```
2. number() thread decides when it's FizzBuzz time:
```java
semFizzBuzz.release();  // Signals fizzbuzz thread to wake up
```

The number() thread is the controller. It checks the current number and signals the appropriate thread (fizz, buzz, or fizzbuzz) to do the printing. That signal is done via .release().

#### 🔄 Roles of Threads

| **Thread**    | **Waits on**               | **Triggered by**                          |
|---------------|----------------------------|-------------------------------------------|
| `fizz()`      | `semFizz.acquire()`        | `semFizz.release()` from `number()`       |
| `buzz()`      | `semBuzz.acquire()`        | `semBuzz.release()` from `number()`       |
| `fizzbuzz()`  | `semFizzBuzz.acquire()`    | `semFizzBuzz.release()` from `number()`   |
| `number()`    | `semNumber.acquire()`      | `semNumber.release()` from worker threads |


#### 🔁 Recap Analogy
- Think of .release() as a green light.
- Think of .acquire() as a driver waiting at a red light.

A thread waiting on .acquire() is paused until another thread releases the permit. Once .release() is called, the waiting thread wakes up and proceeds.

#### ✅ Final Takeaway
- The number() thread calls .release() to wake up the appropriate worker thread.
- The worker thread has already been blocked on .acquire(), waiting for this signal. So calling .release() without
- .acquire() in the same method is perfectly correct in multithreaded coordination.

### 🧵 Here’s How the FizzBuzz Program Ends
#### Main Logic Ends at current > n
In the number() method:
```java
while (current <= n) {
    semNumber.acquire();
    ...
    current++;
}
```
Once current exceeds n, the loop ends.

#### Graceful Exit: Final Releases
Right after the loop ends, we see:

```java
// Graceful exit: signal worker threads to terminate
semFizzBuzz.release();
semFizz.release();
semBuzz.release();
```
🔔 This is crucial. It unblocks the fizz(), buzz(), and fizzbuzz() threads if they are waiting. They’ll wake up, see that current > n, and then break out of their loops.

#### 🔚 In Worker Threads:
Each worker thread checks this condition after acquiring the semaphore:

```java
semFizz.acquire(); // or buzz, fizzbuzz
if (current > n) break;
```

So when the final .release() is called and they wake up:

- They check current > n
- Realize the job is done
- And exit gracefully
