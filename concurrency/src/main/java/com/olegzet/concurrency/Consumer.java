package com.olegzet.concurrency;

import java.util.concurrent.BlockingQueue;

/**
 * Created by oleg.zorin on 21.11.2017.
 */
public class Consumer implements Runnable {
    protected BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}