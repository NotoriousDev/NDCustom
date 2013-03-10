package com.notoriousdev.custom.listeners;

import com.notoriousdev.custom.NDCustom;
import com.notoriousdev.custom.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerListener implements Listener
{

    private final NDCustom plugin;

    public ServerListener(NDCustom plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event)
    {
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.join").replace("{PLAYER}", event.getPlayer().getDisplayName())));
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event)
    {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.quit").replace("{PLAYER}", event.getPlayer().getDisplayName())));
    }

    @EventHandler
    public void onPlayerKick(final PlayerKickEvent event)
    {
        event.setLeaveMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.kick").replace("{PLAYER}", event.getPlayer().getDisplayName())));
    }

    @EventHandler
    public void onPlayerCommand(final PlayerCommandPreprocessEvent event)
    {
        if (event.getMessage().startsWith("/op") && !Permissions.COMMAND_OP.isAuthorised(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.op.player").replace("{PLAYER}", event.getPlayer().getDisplayName())));
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.op.server").replace("{PLAYER}", event.getPlayer().getDisplayName())));
        }
        if (event.getMessage().startsWith("/deop") && !Permissions.COMMAND_DEOP.isAuthorised(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.deop.player").replace("{PLAYER}", event.getPlayer().getDisplayName())));
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.deop.server").replace("{PLAYER}", event.getPlayer().getDisplayName())));
        }
        if (event.getMessage().startsWith("/reload") && !Permissions.COMMAND_RELOAD.isAuthorised(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.reload.player").replace("{PLAYER}", event.getPlayer().getDisplayName())));
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.reload.server").replace("{PLAYER}", event.getPlayer().getDisplayName())));
        }
        if (event.getMessage().startsWith("/stop") && !Permissions.COMMAND_STOP.isAuthorised(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.stop.player").replace("{PLAYER}", event.getPlayer().getDisplayName())));
            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.commands.stop.server").replace("{PLAYER}", event.getPlayer().getDisplayName())));
        }
    }
}
