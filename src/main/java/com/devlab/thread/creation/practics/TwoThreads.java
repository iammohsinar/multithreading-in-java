package com.devlab.thread.creation.practics;

public class TwoThreads {

    public static void main(String[] args) {
        System.out.println("Running in "+Thread.currentThread().getName());

        new Thread(() ->{
            for (int num = 1; num <= 10; num++) {
                System.out.println("Number: " + num + " | " + Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() ->{
            for(char ch = 'A'; ch<='E'; ch++) {
                System.out.println("Letter: " + ch + " | " + Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
}
