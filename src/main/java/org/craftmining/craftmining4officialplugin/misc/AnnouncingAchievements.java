package org.craftmining.craftmining4officialplugin.misc;

import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AnnouncingAchievements implements Listener {
    @EventHandler
    public void announcingAchievementExpectFlyHighElytra(PlayerAdvancementDoneEvent event){
        Player player = event.getPlayer();

        if(event.getAdvancement().getDisplay() == null) return;

        if(event.getAdvancement().getDisplay().getTitle() != null){
            if (event.getAdvancement().getDisplay().getTitle().equals("Sky's the Limit")) {
                if (event.getPlayer().getInventory().getChestplate() != null) {
                    if (event.getPlayer().getInventory().getChestplate().hasItemMeta()) {
                        if (event.getPlayer().getInventory().getChestplate().getItemMeta().hasLocalizedName()) {
                            if (event.getPlayer().getInventory().getChestplate().getItemMeta().getLocalizedName().equals("Temporärer Elytra")) {
                                AdvancementProgress progress = player.getAdvancementProgress(event.getAdvancement());
                                for (String criteria : event.getAdvancement().getCriteria()) {
                                    progress.revokeCriteria(criteria);
                                }
                            }
                        }
                    }
                }
            }
        }

//        Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getDisplayName() +
//                ChatColor.GREEN + " hat das Achievement " +
//                ChatColor.GOLD + event.getAdvancement().getDisplay().getTitle() +
//                ChatColor.GREEN + " erledigt!");
    }
}
