package com.devlab.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1_Example {

  Lock lock = new ReentrantLock();

  public void methodA() {
    System.out.println(Thread.currentThread().getName() + " inside method A");
    lock.lock();
    try {
      Thread.sleep(1000); // let the thread 1 to acquire the lock wait for 1000 to start thread 2;
    } catch (InterruptedException e) {
    }
    System.out.println(Thread.currentThread().getName() + " method A acquired the lock");
    methodB();
    System.out.println("unlocked B");
    lock.unlock();
  }

  public void methodB() {
    System.out.println(Thread.currentThread().getName() + " inside method B");
    lock.lock();
    System.out.println(Thread.currentThread().getName() + " method B acquired the lock");
    methodC();
    lock.unlock(); // comment B method unlock method
  }

  public void methodC() {
    System.out.println(Thread.currentThread().getName() + " inside method C");
    lock.lock();
    System.out.println(Thread.currentThread().getName() + " method C acquired the lock");
    System.out.println("executing the conditions...");
    lock.unlock();
  }

  public static void main(String[] args) throws InterruptedException {
    Lock1_Example e = new Lock1_Example();

    Thread t1 = new Thread(() -> e.methodA(), "Thread 1");
    Thread t2 = new Thread(() -> e.methodA(), "Thread 2");

    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
