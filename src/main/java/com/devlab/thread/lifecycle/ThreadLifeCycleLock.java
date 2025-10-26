package com.devlab.thread.lifecycle;

public class ThreadLifeCycleLock {

    private static final Object LOCK = new Object(); // shared lock for synchronization

    // Thread that sleeps and releases lock later
    static class WorkerThread extends Thread {
        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println(getName() + " acquired LOCK and is RUNNING: " + getState());
                try {
                    Thread.sleep(1500); // hold the lock for 1.5 seconds
                    System.out.println(getName() + " woke up and is about to release LOCK...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(getName() + " finished execution.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WorkerThread thread1 = new WorkerThread();
        thread1.setName("Worker-1");

        WorkerThread thread2 = new WorkerThread();
        thread2.setName("Worker-2");

        // 1️⃣ NEW
        System.out.println("State after creation: " + thread1.getState());

        // 2️⃣ RUNNABLE (after start)
        thread1.start();
        Thread.sleep(200); // small delay to ensure thread1 gets the lock

        // 3️⃣ RUNNABLE (second thread tries to start)
        thread2.start();

        // 4️⃣ Check states
        Thread.sleep(300); // wait a bit for thread2 to try acquiring lock
        System.out.println(thread2.getName() + " state (should be BLOCKED): " + thread2.getState());

        // 5️⃣ TIMED_WAITING (thread1 sleeping inside synchronized)
        System.out.println(thread1.getName() + " state during sleep: " + thread1.getState());

        // 6️⃣ join() - main thread waits for both to finish
        thread1.join();
        thread2.join();

        // 7️⃣ TERMINATED
        System.out.println(thread1.getName() + " final state: " + thread1.getState());
        System.out.println(thread2.getName() + " final state: " + thread2.getState());
    }
}
