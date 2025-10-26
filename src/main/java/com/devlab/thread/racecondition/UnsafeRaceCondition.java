package com.devlab.thread.racecondition;

/*

The Problem — Race Condition

A race condition happens when multiple threads try to read and modify the same variable at the same time.

Let’s visualize what happens in the CPU:

Thread 1:

Reads count (say count = 5)

Adds 1 → (5 + 1 = 6)

Writes 6 back

Thread 2 (at the same time):

Reads count (still sees 5)

Adds 1 → (5 + 1 = 6)

Writes 6 back

Result? Both thought count was 5, and both wrote 6.
👉 One update got lost.

That’s why you see smaller numbers — data corruption due to concurrency.


**/

class Counter{

    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int getCount() {
        return counter;
    }

}

public class UnsafeRaceCondition {

    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();
        Thread t1= new Thread(() ->{
            for (int i = 0; i <10000; i++) {
                counter.increment();
            }
        });

        Thread t2= new Thread(() ->{
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("The expectation is 20000: "+counter.getCount());

    }
}
