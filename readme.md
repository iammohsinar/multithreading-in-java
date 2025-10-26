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

## In ```miniproject``` package:

- Parallel Sum Calculator use of ```ExecutorService```,```Callable```, ```Future```

