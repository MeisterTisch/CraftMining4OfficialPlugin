package org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerLandedEvent implements Listener {
    @EventHandler
    public void playerLandedEvent(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getChestplate() == null) return;
        if(player.getInventory().getChestplate().getType() != Material.ELYTRA) return;
        else if(!player.getInventory().getChestplate().hasItemMeta()) return;
        else if(!player.getInventory().getChestplate().getItemMeta().getLocalizedName().equals("Temporärer Elytra")) return;

        Location loc = event.getTo();
        if(loc == null) return;

        Block block = player.getWorld().getBlockAt(new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()));

        if(block.getType() != Material.AIR) player.getInventory().setChestplate(new ItemStack(Material.AIR));
    }
}
