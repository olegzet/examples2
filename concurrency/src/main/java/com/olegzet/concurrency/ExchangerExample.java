package com.olegzet.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;

/**
 * Created by oleg.zorin on 21.11.2017.
 */
public class ExchangerExample {
    public static void main(String[] args) throws Exception{
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();

        Executors.newFixedThreadPool(10);
    }
}
