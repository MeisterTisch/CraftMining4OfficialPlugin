package org.craftmining.craftmining4officialplugin.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.BoundingBox;

public class NoShitDoingAtSpawn implements Listener {
    BoundingBox boundingBox = new BoundingBox(-505.300,170,-50.700,-479.700,131,-76.300);
    @EventHandler
    public void noHitting(EntityDamageEvent event){
        if(boundingBox.contains(event.getEntity().getBoundingBox())) event.setCancelled(true);
    }
    @EventHandler
    public void noBlockBreaking(BlockBreakEvent event){
        if(boundingBox.contains(event.getBlock().getBoundingBox())) event.setCancelled(true);
    }
    @EventHandler
    public void noBlockPlacing(BlockPlaceEvent event){
        if(boundingBox.contains(event.getBlock().getBoundingBox())) event.setCancelled(true);
    }
}
