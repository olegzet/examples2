package com.olegzet.concurrency;

/**
 * Created by oleg.zorin on 23.11.2017.
 */
public class Incremenator extends Thread {
    private volatile boolean mIsIncrement = true;

    public void changeAction() {
        mIsIncrement = !mIsIncrement;
    }

    @Override
    public void run() {
        do {
            if (!Thread.interrupted()) {
                if (mIsIncrement)
                    Program.mValue++;
                else
                    Program.mValue--;
                System.out.print(Program.mValue + " ");
            } else return;

            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
            }
        } while (true);
    }
}