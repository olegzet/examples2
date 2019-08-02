package com.olegzet.proxy;

public class UserImpl implements User {
    private final String name;

    public UserImpl() {
        this(null);
    }

    public UserImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
