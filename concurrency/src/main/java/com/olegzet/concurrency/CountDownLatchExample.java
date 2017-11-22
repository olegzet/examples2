package com.olegzet.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by oleg.zorin on 21.11.2017.
 */
public class CountDownLatchExample {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);

        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();

        Thread.sleep(4000);
    }
}
