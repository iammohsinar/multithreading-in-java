package com.devlab.thread.lockcondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private static final int CAPACITY = 5;
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

    class Producer implements Runnable {
        @Override
        public void run() {
            int value = 0;
            while (true) {
                lock.lock();
                try {
                    while (buffer.size() == CAPACITY) {
                        System.out.println("Buffer full → Producer waiting...");
                        bufferFull.await(); // same as wait()
                    }
                    buffer.add(value);
                    System.out.println("Produced: " + value++);
                    bufferEmpty.signal(); // wake consumer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (buffer.isEmpty()) {
                        System.out.println("Buffer empty → Consumer waiting...");
                        bufferEmpty.await(); // same as wait()
                    }
                    int val = buffer.poll();
                    System.out.println("Consumed: " + val);
                    bufferFull.signal(); // wake producer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
    }
}
