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
        System.gc();
        System.runFinalization();
        System.out.println(map.size());
    }
}
