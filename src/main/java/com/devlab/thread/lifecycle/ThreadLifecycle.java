package com.devlab.thread.lifecycle;

public class ThreadLifecycle {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread is RUNNING: " + getState());
            try {
                Thread.sleep(1000);
                System.out.println("Thread Woke up still RUNNING...!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread is about to finish");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        // 1️⃣ NEW
        System.out.println("State after creation: " + thread.getState());

        // 2️⃣ RUNNABLE (after start)
        thread.start();
        System.out.println("State after start(): " + thread.getState());

        // 3️⃣ TIMED_WAITING (sleeping)
        Thread.sleep(100); // let child thread start and sleep
        System.out.println("State during sleep(): " + thread.getState());

        // 4️⃣ TERMINATED (after finished)
        thread.join(); // wait for thread to complete
        System.out.println("State after finish: " + thread.getState());


    }
}
