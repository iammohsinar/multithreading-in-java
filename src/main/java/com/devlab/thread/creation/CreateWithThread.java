package com.devlab.thread.creation;

public class CreateWithThread extends Thread{

    @Override
    public void run() {
        System.out.println("Running in "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        CreateWithThread t = new CreateWithThread();
        t.start();
        System.out.println("Running in "+Thread.currentThread().getName());
    }
}
