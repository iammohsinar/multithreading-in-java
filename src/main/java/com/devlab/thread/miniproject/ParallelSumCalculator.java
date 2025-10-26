package com.devlab.thread.miniproject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSumCalculator {

    private static final int SIZE = 20_000_000;
    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) throws Exception {
        int[] data = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            data[i] = i + 1;
        }

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        LocalDateTime start = LocalDateTime.now();

        int chunkSize = SIZE / THREAD_COUNT;
        List<Callable<Long>> tasks = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            int startIdx = i * chunkSize;
            int endIdx = (i == THREAD_COUNT - 1) ? SIZE : startIdx + chunkSize;

            tasks.add(() -> {
                long partialSum = 0;
                for (int j = startIdx; j < endIdx; j++) {
                    partialSum += data[j];
                }
                return partialSum;
            });
        }

        // Submit all tasks at once and get their futures
        List<Future<Long>> results = executor.invokeAll(tasks);

        long total = 0;
        for (Future<Long> f : results) {
            total += f.get();
        }

        LocalDateTime end = LocalDateTime.now();
        long timeMs = Duration.between(start, end).toMillis();

        System.out.println("Final sum: " + total);
        System.out.println("Time taken: " + timeMs + " ms");

        executor.shutdown();
    }
}
