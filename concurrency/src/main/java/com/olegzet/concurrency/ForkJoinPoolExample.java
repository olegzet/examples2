package com.olegzet.concurrency;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by oleg.zorin on 21.11.2017.
 */
public class ForkJoinPoolExample {
    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + mergedResult);
    }
}
