package com.devlab.thread.methods;

class Worker extends Thread {
    private int taskNumber;

    public Worker(String name, int taskNumber) {
        super(name);
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        System.out.println(getName() + " started, priority=" + getPriority());
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " working on step " + i);
            try {
                Thread.sleep(500); // pause for half second
                Thread.yield();   // hint to let other threads run
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " finished task " + taskNumber);
    }
}

public class ThreadMethodsDemo {
    public static void main(String[] args) throws InterruptedException {
        Worker t1 = new Worker("Worker-1", 1);
        Worker t2 = new Worker("Worker-2", 2);

        // Thread settings
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t2.setDaemon(true); // daemon thread

        // Start threads
        t1.start();
        t2.start();

        // Check if threads are alive
        System.out.println(t1.getName() + " alive? " + t1.isAlive());
        System.out.println(t2.getName() + " alive? " + t2.isAlive());

        // Wait for t1 to finish
        t1.join();

        System.out.println("Main thread finished waiting for Worker-1");
    }
}
