package com.devlab.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock2_WithoutUnlockExample {

  Lock lock = new ReentrantLock();

  public void methodA() {
    System.out.println(Thread.currentThread().getName() + " inside method A");
    lock.lock();

    System.out.println(Thread.currentThread().getName() + " method A acquired the lock");
    methodB();
    // lock.unlock(); commentting the unlock to wait thread 1 forever
  }

  public void methodB() {
    System.out.println(Thread.currentThread().getName() + " inside method B");
  }

  public static void main(String[] args) throws InterruptedException {
    Lock2_WithoutUnlockExample e = new Lock2_WithoutUnlockExample();

    Thread t1 = new Thread(() -> e.methodA(), "Thread 1");
    Thread t2 = new Thread(() -> e.methodA(), "Thread 2");

    t1.start();
    Thread.sleep(1000); // let the thread 1 to acquire the lock wait for 1000 to start thread 2;

    t2.start();
    t1.join();
    t2.join();
  }
}
