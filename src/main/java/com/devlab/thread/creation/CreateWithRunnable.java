package com.devlab.thread.creation;

public class CreateWithRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Running in "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t = new Thread(new CreateWithRunnable());
        t.start();
        System.out.println("Running in "+Thread.currentThread().getName());
    }
}
