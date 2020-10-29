package com.olegzet.garbage;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakLinks {
    public static void main(String[] args) {
        Map<Object, String> map = new WeakHashMap<>();
        Object obj = new Object();
        map.put(obj, "object");
        System.out.println(map.size());
        obj = null;
        System.out.println(map.size());
        System.gc();
        System.runFinalization();
        System.out.println(map.size());
        String Str1 = new String("Добро пожаловать на ProgLang.su");
        String Str2 = new String("Добро пожаловать на ProgLang.su");

        System.out.print("Каноническое представление Str1: " );
        System.out.println(Str1.intern());

        System.out.print("Каноническое представление Str2: " );
        System.out.println(Str2.intern());

        System.out.println(Str1.intern() == Str2.intern());

    }
}
