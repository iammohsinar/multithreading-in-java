package com.devlab.thread.miniproject;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
  private final AtomicInteger balance = new AtomicInteger(1000);
  private final ReentrantLock lock = new ReentrantLock();

  public void deposit(int amount) {
    lock.lock();
    try {
      balance.addAndGet(amount);
      System.out.println(
          "The person "
              + Thread.currentThread().getName()
              + " deposited amount: "
              + amount
              + " the balance is "
              + balance.get());
      // Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void withDraw(int amount) {
    //        try {
    //            Thread.sleep(100);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    if (lock.tryLock()) {
      try {
        if (balance.get() >= amount) {
          balance.addAndGet(-amount);
          System.out.println(
              "The person "
                  + Thread.currentThread().getName()
                  + " withdraw amount: "
                  + amount
                  + " the balance is "
                  + balance.get());
        } else {
          System.out.println(
              Thread.currentThread().getName()
                  + " tried to withdraw "
                  + amount
                  + " but "
                  + "insufficient balance.");
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    } else {
      System.out.println(
          "Sorry, can not withdraw amount "
              + amount
              + " because other "
              + "transaction is in progress..!!");
    }
  }

  public int showBalance() {
    return balance.get();
  }
}

public class BankAccountSimulation {

  public static void main(String[] args) throws InterruptedException {
    BankAccount account = new BankAccount();
    System.out.println("OPENNING BALANCE: " + account.showBalance());
    Thread youtubeIncome = new Thread(() -> account.deposit(200), "youtubeIncome");

    Thread freelancerIncome = new Thread(() -> account.deposit(500), "freelancerIncome");

    Thread cashDeposit = new Thread(() -> account.deposit(300), "cashDeposit");

    Thread onlinePayment = new Thread(() -> account.withDraw(200), "onlinePayment");

    Thread cashWithDraw = new Thread(() -> account.withDraw(500), "cashWithDraw");

    Thread onlinePurchase = new Thread(() -> account.withDraw(300), "onlinePurchase");

    // ------ deposit
    youtubeIncome.start();
    onlinePayment.start();

    freelancerIncome.start();
    cashWithDraw.start();

    cashDeposit.start();
    onlinePurchase.start();

    // main should wait until all thread completed its work
    // ------ deposit
    youtubeIncome.join();
    onlinePurchase.join();

    onlinePayment.join();
    freelancerIncome.join();

    cashWithDraw.join();
    cashDeposit.join();

    Thread.sleep((long) (Math.random() * 200));
    System.out.println("Closing BALANCE: " + account.showBalance());
    System.out.println("Ending main thread");
  }
}
