package com.devlab.thread.racecondition;



/*

the expected balance after executing following program is ZERO
when you execute it without synchronized block it will not be ZERO

**/


class BankOfAmerica {

    private int balance = 2000000;
    private final Object LOCK = new Object();

    public void withdraw(int amount) {
        synchronized (LOCK) {
            balance -=amount;
        }
    }

    public int getBalance() {
        return balance;
    }

}

public class BankAccount {
    public static void main(String[] args) throws InterruptedException {
        BankOfAmerica boa = new BankOfAmerica();
        System.out.println("Open Balance of Account: "+boa.getBalance());

        // User withdraw from ATM
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 10000; i++) {
                boa.withdraw(100);
            }
        });

        // User withdraw from Mobile APP
        Thread t2 = new Thread(() ->{
            for (int i = 0; i < 10000; i++) {
                boa.withdraw(100);
            }
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("The expected balance of Account after withdraw (0) : "+boa.getBalance());

    }

}