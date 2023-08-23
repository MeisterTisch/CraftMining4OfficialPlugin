package org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryMoveListener implements Listener {
    @EventHandler
    public void playerMovesItemEvent(InventoryClickEvent event){
        if(event.getCurrentItem().getType() == Material.ELYTRA){
            if(event.getCurrentItem().hasItemMeta()){
                if(event.getCurrentItem().getItemMeta().getLocalizedName() != null && event.getCurrentItem().getItemMeta().hasLocalizedName()){
                    if(event.getCurrentItem().getItemMeta().getLocalizedName().equals("Temporärer Elytra")){
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
