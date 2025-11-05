package com.devlab.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock4_TryLockTimeoutExample {

  Lock lock = new ReentrantLock();

  public void methodA() {
    System.out.println(Thread.currentThread().getName() + " inside method A");
    try {
    if(lock.tryLock(6000, TimeUnit.MILLISECONDS)) {
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
} catch (InterruptedException e) {}
  }

  public void methodB() {
    System.out.println(Thread.currentThread().getName() + " inside method B");
    try{
    Thread.sleep(3000);
    }catch (InterruptedException e) {}
   }


  public static void main(String[] args) throws InterruptedException {
    Lock4_TryLockTimeoutExample e = new Lock4_TryLockTimeoutExample();

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
