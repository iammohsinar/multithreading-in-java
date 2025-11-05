package com.devlab.thread.lock.synchronizedkeyword;

public class SynchronizedBlock1_Example {

  Object object = new Object();

  public void methodA() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + " inside method A");
      try {
        Thread.sleep(1000); // let the thread 1 to acquire the lock wait for 1000 to start thread 2;
      } catch (InterruptedException e) {
      }
      System.out.println(Thread.currentThread().getName() + " method A acquired the lock");
      methodB();
    }
  }

  public void methodB() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + " inside method B");
      System.out.println(Thread.currentThread().getName() + " method B acquired the lock");
      methodC();
    }
  }

  public void methodC() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + " inside method C");
      System.out.println(Thread.currentThread().getName() + " method C acquired the lock");
      System.out.println("executing the conditions...");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    SynchronizedBlock1_Example e = new SynchronizedBlock1_Example();

    Thread t1 = new Thread(() -> e.methodA(), "Thread 1");
    Thread t2 = new Thread(() -> e.methodA(), "Thread 2");

    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
