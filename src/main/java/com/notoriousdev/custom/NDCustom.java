package com.notoriousdev.custom;

import org.bukkit.plugin.java.JavaPlugin;

public class NDCustom extends JavaPlugin
{

    public final NDCPlayerListener pl = new NDCPlayerListener(this);

    @Override
    public void onDisable()
    {
        getLogger().info("NDCustom Disabled!");
    }

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new NDCPlayerListener(this), this);
        getLogger().info("NDCustom Enabled!");
    }
}
