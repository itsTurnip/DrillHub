package com.protonmail.itsTurnip.DrillHub;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class HubListener implements Listener {
    private DrillHub plugin;

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        switch(e.getCause()) {
            case FIRE:
            case FIRE_TICK:
                if (!e.isCancelled())
                    e.setCancelled(true);
                e.getEntity().setFireTicks(0);
                break;
            case SUFFOCATION:
                if (e.getEntity() instanceof Player) {
                    Player player = (Player) e.getEntity();
                    player.teleport(e.getEntity().getLocation().getWorld().getSpawnLocation());
                    if (!e.isCancelled())
                        e.setCancelled(true);
                }
                break;
            default:
                if(!e.isCancelled())
                    e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Location loc = e.getTo();
        if (loc.getBlockY() < 0) {
            e.getPlayer().teleport(loc.getWorld().getSpawnLocation());
        }
    }
    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if(!e.isCancelled())
                e.setCancelled(true);
            if(player.getFoodLevel() < 20)
                player.setFoodLevel(20);
        }
    }
    @EventHandler
    public void onBlockFade(BlockFadeEvent e) {
        Material material = e.getBlock().getBlockData().getMaterial();
        switch(material) {
            case SNOW:
            case BRAIN_CORAL:
            case BUBBLE_CORAL:
            case FIRE_CORAL:
            case HORN_CORAL:
            case TUBE_CORAL:
            case BRAIN_CORAL_BLOCK:
            case BRAIN_CORAL_FAN:
            case BRAIN_CORAL_WALL_FAN:
            case BUBBLE_CORAL_BLOCK:
            case BUBBLE_CORAL_FAN:
            case BUBBLE_CORAL_WALL_FAN:
            case FIRE_CORAL_BLOCK:
            case FIRE_CORAL_FAN:
            case FIRE_CORAL_WALL_FAN:
            case HORN_CORAL_BLOCK:
            case HORN_CORAL_FAN:
            case HORN_CORAL_WALL_FAN:
            case TUBE_CORAL_BLOCK:
            case TUBE_CORAL_FAN:
            case TUBE_CORAL_WALL_FAN:
            case ICE:
                if (!e.isCancelled())
                    e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
        e.setJoinMessage(null);
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND &&
                !plugin.getPerms().has(e.getPlayer(), "drillhub.armorstands.modify") &&
                !e.isCancelled())
            e.setCancelled(true);
    }
    HubListener(DrillHub plugin) {
        this.plugin = plugin;
    }
}
