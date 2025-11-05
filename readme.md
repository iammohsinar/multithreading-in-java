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

## The ```synchronized``` & ```reentrantlock```: 

There are mainly two types of lock in java `Intrinsic` and `explicit` lock. Every java object including the object class has uses the `intrinsic` lock
- When synchronized keyword is being used the java primarly use the intrinsic lock.
  - the synchronized uses `intrinsic` lock on object to acquire the lock. The `thread` has to wait indefinitely for its turn.  
- We (developer) has not control over it to `unlock` or `timeout`, it that scenario we have `explicit` lock.
- In Java we have `Lock` interface and `ReentrantLock` implementor.
- In `Lock` we have multiple methods but mainly I used `lock()`, `unlock()`, `trylock()`, `lockInterruptibly()` [trylock(long time, TimeUnit unit)](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html#tryLock-long-java.util.concurrent.TimeUnit-)

### `Explicit` (Lock) and `Intrinsic` (Synchronized)

- The thread who acquires the lock is owner of the lock, the same thread can acquire as many locks as it can, however it has to unlock those same locks as many times it has acquired.
- Internally the initial value is `0` when you use the `lock()` it increment by `1` if the same thread acquire `3` locks  it has to `unlock()` 3 times to release the complete lock.  if you miss any `unlock()` method, then program will hang up forever try program `Lock1_Example` and comment the line `// comment B method unlock method`, but that's not in synchronized block check example `SynchronizedBlock2_Example` once thread enters in synchronized block it will complete the entire block / method body because we do not have control over it, that's why it is called intrinsic lock.  

### `tryLock()` An immediate timeout:

- If there are two threads thread-1 and thread-2. When thread-1 acquire the lock and thread-2 will try for the lock but thread-1 already has it, so the `tryLock()` will return `false` and thread-2 will not wait but immediately stop. try example `Lock3_TryLockExample`, With `synchronized` thread-2 always waits until the lock is released, no option to skip or timeout.

### `tryLock(long time, TimeUnit unit)` Wait for given time:

- The thread-2 will wait for given time, if it gets lock then it will complete the task otherwise, it will be stopped. try example `Lock4_TryLockTimeoutExample`

## In ```miniproject``` package:

1. **Parallel Sum Calculator** use of ```ExecutorService```,```Callable```, ```Future```
2. **Bank Account Simulation** use of `Lock`, `Reentrantlock`,`AtomicInteger`
   - Multiple threads deposit and withdraw safely. 
   - To check the difference in **output** uncomment the code from line `25` to `29`
   - ```
     //        try {
     //            Thread.sleep(100);
     //        } catch (InterruptedException e) {
     //            e.printStackTrace();
     //        }
     ```
