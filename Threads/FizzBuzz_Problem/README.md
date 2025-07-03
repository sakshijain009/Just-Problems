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
