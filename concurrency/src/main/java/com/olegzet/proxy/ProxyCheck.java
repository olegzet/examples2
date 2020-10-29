package com.olegzet.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyCheck {
    public static void main(String[] args) {
        User user = new UserImpl("Вася");

        InvocationHandler handler = (proxy, method, args) -> {
            if (method.getName().equals("getName")) {
                return ((String) method.invoke(user, args)).toUpperCase();
            }
            return method.invoke(user, args);
        };

        User userProxy = (User) Proxy.newProxyInstance(user.getClass().getClassLoader(), User.class.getInterfaces(), handler);
        System.out.println(userProxy.getName());
    }
}
