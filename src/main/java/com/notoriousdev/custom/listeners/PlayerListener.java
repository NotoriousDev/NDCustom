package com.notoriousdev.custom.listeners;

import com.notoriousdev.custom.NDCustom;
import com.notoriousdev.custom.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlayerListener implements Listener
{

    private final NDCustom plugin;
    private final FileConfiguration cfg;
    private Map<Player, Long> lastMessage = new HashMap<Player, Long>();

    public PlayerListener(NDCustom plugin)
    {
        this.plugin = plugin;
        this.cfg = plugin.getConfig();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        if (Permissions.BYPASS.isAuthorised(player))
        {
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE && (cfg.getList("itemblock.place").contains(block.getType().toString().toLowerCase())))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot place " + block.getType()
                    .name().toLowerCase().replace("_", " ") + "s in creative!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemUse(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (Permissions.BYPASS.isAuthorised(player))
        {
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE && (cfg.getList("itemblock.throw").contains(item.getType().toString().toLowerCase())) && (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot use " + item.getType()
                    .name().toLowerCase().replace("_", " ") + "s in creative because people spam them!");
        }
        if (item.getType() == Material.WRITTEN_BOOK)
        {
            /*
             BookMeta bm = (BookMeta) item.getItemMeta();
             if (cfg.getStringList("messages.alerts.books").contains(bm.getTitle()))
             {
             for (Player staff : plugin.getServer().getOnlinePlayers())
             {
             if (Permissions.BOOK.isAuthorised(staff))
             {
             staff.sendMessage(ChatColor.DARK_RED + "[ALERT] " + ChatColor.DARK_GRAY + "|| " + ChatColor.GREEN + event.getPlayer().getName() + " is reading a book...");
             }
             }
             }
             */
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBowFire(EntityShootBowEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            Player player = (Player) event.getEntity();
            if (Permissions.BYPASS.isAuthorised(player))
            {
                return;
            }
            if (player.getGameMode() == GameMode.CREATIVE)
            {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You cannot use bows in creative!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent event)
    {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
        {
            if (Permissions.BYPASS.isAuthorised(event.getPlayer()))
            {
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot drop items in creative because people spam them!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDispenserInteract(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if (Permissions.BYPASS.isAuthorised(player))
        {
            return;
        }
        ItemStack item = event.getCurrentItem();
        if (item != null && player.getGameMode() == GameMode.CREATIVE
                && event.getInventory().getType() == InventoryType.DISPENSER
                && (cfg.getList("itemblock.dispenser").contains(item.getType().toString().toLowerCase())))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot put " + item.getType()
                    .name().toLowerCase().replace("_", " ") + "s into dispensers because people spam them!");
            event.setCursor(null);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreativeDamage(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player)
        {
            Player player = (Player) event.getDamager();
            if (Permissions.BYPASS.isAuthorised(player))
            {
                return;
            }
            if (player.getGameMode() == GameMode.CREATIVE)
            {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You cannot PVP while in creative mode, dumbass!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSkyblockDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        if (player.getLocation().getWorld().getName().equalsIgnoreCase("skyblock"))
        {
            if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID)
            {
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.skyblock-fall").replace("{PLAYER}", player.getDisplayName())));
            }
            else if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL)
            {
                int dmg = player.getLastDamageCause().getDamage();
                float dist = player.getFallDistance();
                double velocity = Math.sqrt(2 * 9.81 * dist * 72);
                double joules = 9.81 * 72 * dist;
                String sjoules = String.valueOf(joules);
                String svel = String.valueOf(velocity);
                svel = svel.length() > 5 ? svel.substring(0, 5) : svel;
                sjoules = sjoules.length() > 5 ? sjoules.substring(0, 5) : sjoules;
                String sdist = String.valueOf(Math.floor(dist));
                event.setDeathMessage(ChatColor.RED + "[DEATH] " + ChatColor.DARK_GRAY + "|| " + ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + " fell " + sdist + " blocks, and took " + sjoules + " joules to the feet");
            }
            else
            {
                // Random death messages? Random death messages.
                event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.generic-death").replace("{PLAYER}", player.getDisplayName())));
            }
        }
        else
        {
            // Random death messages? Random death messages.
            event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', cfg.getString("messages.generic-death").replace("{PLAYER}", player.getDisplayName())));
        }
    }

    @EventHandler //(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message = event.getMessage();

        /*
         if(message.length() > 6)
         {
         ((AsyncPlayerChatEvent)event).setMessage(message.toLowerCase());
         }
         */


        if (Permissions.CHAT_BYPASS.isAuthorised(player) || Permissions.BYPASS.isAuthorised(player))
        {
            return;
        }
        if (Permissions.CHAT.isAuthorised(player))
        {
            Long current = new Date().getTime();
            if (lastMessage.containsKey(player))
            {
                if (current - lastMessage.get(player) > 3000 && !event.isCancelled())
                {
                    // Allow chat
                    lastMessage.put(player, current);
                }
                else
                {
                    // Deny chat
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.GREEN + "You may only speak once every 3 seconds!");
                    player.sendMessage(ChatColor.GREEN + "We do this to prevent spam.");
                    plugin.getLogger().info(player.getName() + " tried to chat but was blocked!");
                }
            }
            else
            {
                lastMessage.put(player, current);
            }
        }
        else
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "YOU SHALL NOT SPEAK!");
            plugin.getLogger().info(player.getName() + " tried to chat but was blocked!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHangingBreak(HangingBreakEvent event)
    {
        if (event.getEntity() instanceof ItemFrame)
        {
            ((ItemFrame) event.getEntity()).setItem(null);
        }
        event.getEntity().remove();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityHangingBreak(HangingBreakByEntityEvent event)
    {
        if (event.getRemover() instanceof TNTPrimed)
        {
            event.setCancelled(true);
            return;
        }
        if (event.getEntity() instanceof ItemFrame)
        {
            ((ItemFrame) event.getEntity()).setItem(null);
        }
        event.getEntity().remove();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHopperMove(InventoryMoveItemEvent event)
    {
        if (event.getDestination().getType() == InventoryType.DISPENSER || event.getDestination().getType() == InventoryType.DROPPER)
        {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChestBreak(BlockBreakEvent event)
    {
        if (event.getBlock().getType() == Material.CHEST)
        {
            event.getBlock().setType(Material.AIR);
            event.setCancelled(true);
        }
    }
}
