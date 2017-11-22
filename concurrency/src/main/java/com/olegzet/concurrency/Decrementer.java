package com.olegzet.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by oleg.zorin on 21.11.2017.
 */
public class Decrementer implements Runnable {
    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
            Thread.sleep(1000);
            System.out.println("1s");
            this.latch.countDown();

            Thread.sleep(1000);
            System.out.println("2s");
            this.latch.countDown();

            Thread.sleep(1000);
            System.out.println("3s");
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
