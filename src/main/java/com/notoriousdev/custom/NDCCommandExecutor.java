package com.notoriousdev.custom;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NDCCommandExecutor implements CommandExecutor
{

    public NDCustom plugin;

    public NDCCommandExecutor(NDCustom plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("ndcustom") && sender.hasPermission("ndcustom.admin"))
        {
            if (args.length == 0)
            {
                sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getName());
                sender.sendMessage(ChatColor.GREEN + "Version: " + plugin.getDescription().getVersion());
                return true;
            }

            if (args.length > 1)
            {
                sender.sendMessage(ChatColor.RED + "Invalid amount of args!");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload"))
            {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
                return true;
            }
        }
        return false;
    }
}

