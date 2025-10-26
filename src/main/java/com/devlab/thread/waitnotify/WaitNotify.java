package com.devlab.thread.waitnotify;


import java.util.LinkedList;
import java.util.Queue;


public class WaitNotify {


    private final Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    // Producer Thread
    class Producer extends Thread {
        @Override
        public void run() {
            int value = 0;
            try {
                while (true) {
                    synchronized (buffer) {
                        // If buffer full → wait
                        while (buffer.size() == CAPACITY) {
                            System.out.println("Buffer full → Producer waiting...");
                            buffer.wait(); // release lock & wait
                        }

                        System.out.println("Produced: " + value);
                        buffer.add(value++);
                        buffer.notify(); // notify consumer
                    }
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Consumer Thread
    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (buffer) {
                        // If buffer empty → wait
                        while (buffer.isEmpty()) {
                            System.out.println("Buffer empty → Consumer waiting...");
                            buffer.wait(); // release lock & wait
                        }

                        int value = buffer.poll();
                        System.out.println("Consumed: " + value);
                        buffer.notify(); // notify producer
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotify example = new WaitNotify();
        new Thread(example.new Producer()).start();
        new Thread(example.new Consumer()).start();
    }
}

