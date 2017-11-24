package com.olegzet.concurrency;

/**
 * Created by oleg.zorin on 23.11.2017.
 */
public class CheckVoice {
    static EggVoice mAnotherOpinion;    //Побочный поток

    public static void main(String[] args) {
        mAnotherOpinion = new EggVoice();    //Создание потока
        System.out.println("Спор начат...");
        mAnotherOpinion.start();            //Запуск потока

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("курица!");
        }

        if (mAnotherOpinion.isAlive())    //Если оппонент еще не сказал последнее слово
        {
            try {
                mAnotherOpinion.join();    //Подождать пока оппонент закончит высказываться.
            } catch (InterruptedException e) {
            }

            System.out.println("Первым появилось яйцо!");
        } else    //если оппонент уже закончил высказываться
        {
            System.out.println("Первой появилась курица!");
        }
        System.out.println("Спор закончен!");
    }
}
