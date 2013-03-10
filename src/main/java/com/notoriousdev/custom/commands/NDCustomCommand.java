package com.notoriousdev.custom.commands;

import com.notoriousdev.custom.NDCustom;
import com.notoriousdev.custom.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        if (label.equalsIgnoreCase("ndcustom") && Permissions.COMMAND_NDCUSTOM.isAuthorised(sender)) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    sender.sendMessage(String.format("%s[%s] Version: %s", ChatColor.GREEN, plugin.getName(), plugin.getDescription().getVersion()));
                    sender.sendMessage(String.format("%s/ndcustom [reload|unload]", ChatColor.GREEN));
                    return true;
                } else {
                    plugin.getLogger().info(String.format("Version: %s", plugin.getDescription().getVersion()));
                    plugin.getLogger().info(String.format("ndcustom [reload|unload]"));
                    return true;
                }
            } else if (args.length > 1) {
                if (sender instanceof Player) {
                    sender.sendMessage(String.format("%s[%s] Too many arguments!", ChatColor.RED, plugin.getName()));
                    sender.sendMessage(String.format("%s/ndcustom [reload|unload]", ChatColor.RED));
                    return true;
                } else {
                    plugin.getLogger().info(String.format("Too many arguments!"));
                    plugin.getLogger().info(String.format("ndcustom [reload|unload]"));
                    return true;
                }
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    if (sender instanceof Player) {
                        sender.sendMessage(String.format("%s[%s] Config successfully reloaded!", ChatColor.GREEN, plugin.getName()));
                    } else {
                        plugin.getLogger().info(String.format("Config successfully reloaded!"));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("unload")) {
                    if (sender instanceof Player) {
                        sender.sendMessage(String.format("%s[%s] Unloading...", ChatColor.GREEN, plugin.getName()));
                    } else {
                        plugin.getLogger().info(String.format("Unloading..."));
                    }
                    plugin.getPluginLoader().disablePlugin(plugin);
                    return true;
                } else {
                    if (sender instanceof Player) {
                        sender.sendMessage(String.format("%s[%s] Argument not recognised.", ChatColor.RED, plugin.getName()));
                        sender.sendMessage(String.format("%s/ndcustom [reload|unload]", ChatColor.RED));
                        return true;
                    } else {
                        plugin.getLogger().info(String.format("Argument not recognised."));
                        plugin.getLogger().info(String.format("ndcustom [reload|unload]"));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
