package com.devlab.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock3_TryLockExample {

  Lock lock = new ReentrantLock();

  public void methodA() {
    System.out.println(Thread.currentThread().getName() + " inside method A");
    if(lock.tryLock()) {
        try{
            System.out.println(Thread.currentThread().getName() + " method A acquired the lock");
            methodB();
            System.out.println(Thread.currentThread().getName() + " method A completed the task");
        } finally{
            lock.unlock();
        }
    } else {
      System.out.println(Thread.currentThread().getName()+" unable to wait");
    }

  }

  public void methodB() {
    System.out.println(Thread.currentThread().getName() + " inside method B");
    try{
    Thread.sleep(10000);
    }catch (InterruptedException e) {}
   }


  public static void main(String[] args) throws InterruptedException {
    Lock3_TryLockExample e = new Lock3_TryLockExample();

    Thread t1 = new Thread(() -> e.methodA(), "Thread 1");
    Thread t2 = new Thread(() -> e.methodA(), "Thread 2");

    t1.start();
          // let the thread 1 to get lock first
          Thread.sleep(100);
    t2.start();
    t1.join();
    t2.join();
  }
}
