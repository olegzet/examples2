package com.olegzet.concurrency;

/**
 * Created by oleg.zorin on 23.11.2017.
 */
public class Program {
    public static int mValue = 0;

    static Incremenator mInc;

    public static void main(String[] args) {
        mInc = new Incremenator();
        System.out.print("Значение = ");
        mInc.start();

        for (int i = 1; i <= 2; i++) {
            try {
                Thread.sleep(i * 2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mInc.changeAction();
        }

        mInc.interrupt();
    }
}
