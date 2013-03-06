package com.notoriousdev.custom;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class NDCPlayerListener implements Listener {

    public NDCustom plugin;

    public NDCPlayerListener(NDCustom plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event){
        event.setJoinMessage(ChatColor.GREEN + "Join: " + ChatColor.GOLD + event.getPlayer().getName());

    }
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event){
        event.setQuitMessage(ChatColor.RED + "Quit: " + ChatColor.GOLD + event.getPlayer().getName());

    }
    @EventHandler
    public void onPlayerKick(final PlayerKickEvent event){
        event.setLeaveMessage(ChatColor.RED + "Kick: " + ChatColor.GOLD + event.getPlayer().getName());

    }
}
