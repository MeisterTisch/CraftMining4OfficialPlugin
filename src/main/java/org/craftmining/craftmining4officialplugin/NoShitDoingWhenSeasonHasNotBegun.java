package org.craftmining.craftmining4officialplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoShitDoingWhenSeasonHasNotBegun implements Listener {
    public final CraftMining4OfficialPlugin plugin;

    public NoShitDoingWhenSeasonHasNotBegun(CraftMining4OfficialPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void noHitting(EntityDamageEvent event){
        if(plugin.getConfig().getBoolean("hasSeasonBegun")){

        } else {

        }
    }

    @EventHandler
    public void noBlockBreaking(BlockBreakEvent event){
        if(plugin.getConfig().getBoolean("hasSeasonBegun")){

        } else {

        }
    }
    @EventHandler
    public void noBlockPlacing(BlockPlaceEvent event){
        if(plugin.getConfig().getBoolean("hasSeasonBegun")){

        } else {

        }
    }
}
