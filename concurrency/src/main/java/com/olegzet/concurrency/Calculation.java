package com.olegzet.concurrency;

import java.util.ArrayList;

/**
 * Created by oleg.zorin on 07.12.2017.
 */
public class Calculation {
    static int a = 1111;

    static {
        a = a-- - --a;
    }

    {
        a = a++ + ++a;
    }

    public static void main(String[] args) {
        new Runnable() {
            @Override
            public void run() {

            }
        };
        new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        Integer.valueOf(127);
        new Integer(null);
        //new String(null);
        System.out.println(a);
    }
}
