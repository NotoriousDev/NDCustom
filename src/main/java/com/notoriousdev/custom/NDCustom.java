package com.notoriousdev.custom;

//import com.notoriousdev.custom.database.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public class NDCustom extends JavaPlugin
{

    //public final NDCPlayerListener pl = new NDCPlayerListener(this);
    //public final MySQL sql = new MySQL(this);
    //public final Utils utils = new Utils(this);

    @Override
    public void onDisable()
    {
        getLogger().info("NDCustom Disabled!");
    }

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new NDCPlayerListener(this), this);
        getCommand("ndcustom").setExecutor(new NDCCommandExecutor(this));
        getLogger().info("NDCustom Enabled!");
    }
}
