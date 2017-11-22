package com.olegzet.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by oleg.zorin on 21.11.2017.
 */
public class Waiter implements Runnable {
    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter Released");
    }
}
