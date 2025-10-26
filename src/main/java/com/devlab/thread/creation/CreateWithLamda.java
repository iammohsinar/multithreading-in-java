package com.devlab.thread.creation;

public class CreateWithLamda {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Running in "+Thread.currentThread().getName());
        }).start();

        System.out.println("Running in "+Thread.currentThread().getName());
    }
}
