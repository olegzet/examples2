package com.olegzet.mservice;

interface TurnService {

    String turnOn(String id);

    UserDTO attachUser(String user, String lampId);
}
