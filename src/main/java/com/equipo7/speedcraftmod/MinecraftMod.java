package com.equipo7.speedcraftmod;

abstract class MinecraftMod {
    protected int name;
    protected int version;
    protected static final String MOD_ID = "speedcraftmod";


    protected abstract void setUpScreen();
    protected abstract void nextReset();
    protected abstract void setController();
    protected abstract void setConnector();
    protected abstract void stopReset();
}
