package com.notoriousdev.custom.commands;

import com.notoriousdev.custom.NDCustom;
import com.notoriousdev.custom.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NDCustomCommand implements CommandExecutor
{

    private final NDCustom plugin;

    public NDCustomCommand(NDCustom plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("ndcustom") && Permissions.COMMAND_NDCUSTOM.isAuthorised(sender))
        {
            if (args.length == 0)
            {
                sender.sendMessage(String.format("%s[%s] Version: %s", ChatColor.GREEN, plugin.getName(), plugin.getDescription().getVersion()));
                sender.sendMessage(String.format("%s/ndcustom [reload|unload]", ChatColor.GREEN));
                return true;
            } else if (args.length > 1)
            {
                sender.sendMessage(String.format("%s[%s] Too many arguments!", ChatColor.RED, plugin.getName()));
                sender.sendMessage(String.format("%s/ndcustom [reload|unload]", ChatColor.RED));
                return true;
            } else
            {
                if (args[0].equalsIgnoreCase("reload"))
                {
                    plugin.reloadConfig();
                    sender.sendMessage(String.format("%s[%s] Config successfully reloaded!", ChatColor.GREEN, plugin.getName()));
                    return true;
                } else if (args[0].equalsIgnoreCase("unload"))
                {
                    sender.sendMessage(String.format("%s[%s] Unloading...", ChatColor.GREEN, plugin.getName()));
                    plugin.getPluginLoader().disablePlugin(plugin);
                    return true;
                } else
                {
                    sender.sendMessage(String.format("%s[%s] Argument not recognised.", ChatColor.RED, plugin.getName()));
                    sender.sendMessage(String.format("%s/ndcustom [reload|unload]", ChatColor.RED));
                    return true;
                }
            }
        }
        return false;
    }
}
