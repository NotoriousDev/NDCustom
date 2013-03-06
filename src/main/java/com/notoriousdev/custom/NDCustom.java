package com.notoriousdev.custom;

import org.bukkit.plugin.java.JavaPlugin;

public class NDCustom extends JavaPlugin
{

    @Override
    public void onDisable()
    {
    }

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new NDCPlayerListener(this), this);
        new NDCPlayerListener(this);
    }
}
