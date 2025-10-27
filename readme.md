# 🧵 Java Multithreading — Core Synchronization & Communication Concepts

## In ```Creation``` package: 
### 🔍 Understand how threads work, are created, and controlled.

- A thread is the smallest unit of execution inside a process.
- Every Java program starts with one thread the **main thread**.

#### ⚙️ Think of it like:
- A process = a company.
- A thread = an employee

####  How to Create a Thread (3 ways)
- ```CreateWithLamda.java```
- ```CreateWithRunnable.java```
- ```CreateWithThread.java```

## In ```Lifecycle``` package:
### 🔍 Understand the life cycle of thread.

NEW → RUNNABLE → RUNNING → BLOCKED → TERMINATED

- **NEW**
  - When thread is created but not started yet.
  - Thread object exists, but start() hasn’t been called.
- **RUNNABLE**
  - When CPU starts executing the thread’s run() method.
  - The thread is actively executing code.
- **BLOCKED / WAITING / TIMED_WAITING**
  - When the thread is paused
  - Happens during sleep(), wait(), or waiting for lock.
- **TERMINATED (DEAD)**
  - When run() finishes or throws an exception.
  - The thread has completed execution.

## In ```Methods``` package:
### 🔍 Understand the method present in ```Thread```.

```sleep()```, ```join()```, ```yield()```, ```setPriority()```, ```getPriority()```,
```isAlive()```, ```getName()```, ```setName()```

- ```join()``` 
  - You have 3 threads:
    Imagine we have three threads: T1, T2, and T3. Now, suppose T1 calls `T2.join()`. This means that T1 will pause its execution and wait until T2 finishes its work. While T1 is waiting, T2 continues to run normally, and T3 remains completely independent, continuing its work without any interruption. Once T2 finishes execution, the `join()` call in T1 unblocks, allowing T1 to resume from where it left off. This mechanism is often used when one thread needs to wait for another to complete before proceeding.


- ```yeild()```
  - It tells the CPU scheduler it’s willing to pause. The thread goes from Running → Runnable (ready state).  The scheduler may pick another thread of equal or higher priority to run next. Or, it might immediately pick the same thread again because it’s only a suggestion, not a command. The CPU scheduler may or may not listen to it. — so sometimes it looks like nothing happened. 

- ```sleep(ms)```
  - Actually pauses for a fixed time.
 

## In ```miniproject``` package:

- Parallel Sum Calculator use of ```ExecutorService```,```Callable```, ```Future```
- Bank Account Simulation use of
  - `Lock`, `Reentrantlock`,`AtomicInteger`
  - Multiple threads deposit and withdraw safely. 
  - To check the difference in **output** uncomment the code from line `25` to `29`
  - ```
    //        try {
    //            Thread.sleep(100);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    ```
