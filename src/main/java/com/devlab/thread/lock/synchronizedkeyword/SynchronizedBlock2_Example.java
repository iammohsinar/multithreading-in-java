package com.devlab.thread.lock.synchronizedkeyword;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBlock2_Example {

  Object object = new Object();

  public void methodA() {
    System.out.println(Thread.currentThread().getName() + " inside method A");
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + " method A acquired the lock");
      methodB();
    }
  }

  public void methodB() {
    System.out.println(Thread.currentThread().getName() + " inside method B");
   }


  public static void main(String[] args) throws InterruptedException {
    SynchronizedBlock2_Example e = new SynchronizedBlock2_Example();

    Thread t1 = new Thread(() -> e.methodA(), "Thread 1");
    Thread t2 = new Thread(() -> e.methodA(), "Thread 2");

    t1.start();
          Thread.sleep(1000); // let the thread 1 to acquire the lock wait for 1000 to start thread 2;

    t2.start();
    t1.join();
    t2.join();
  }
}
