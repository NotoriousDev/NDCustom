package com.notoriousdev.custom;

import com.notoriousdev.custom.commands.NDCustomCommand;
import com.notoriousdev.custom.listeners.PlayerListener;
import com.notoriousdev.custom.listeners.ServerListener;

import java.io.File;

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
        if (!new File(getDataFolder(), "config.yml").exists())
        {
            getLogger().warning("Config not found! Saving default config.yml. Please edit the file and restart the server.");
            saveDefaultConfig();
            getPluginLoader().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ServerListener(this), this);
        getCommand("ndcustom").setExecutor(new NDCustomCommand(this));
    }
}
