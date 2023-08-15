package org.craftmining.craftmining4officialplugin.newPlayers.rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.newPlayers.Intros;

public class MoveListener implements Listener {
   @EventHandler
   public void antiMoveNoRules(PlayerMoveEvent event){
       if(!PlayerManagerFile.getConfig().getBoolean(event.getPlayer().getDisplayName() + ".hasAcceptedRules")){
           event.setCancelled(true);
       } else if (Intros.getPlayerList().contains(event.getPlayer())){
           if(event.getFrom().getX() != event.getTo().getX() ||
           event.getFrom().getY() != event.getTo().getY() ||
           event.getFrom().getZ() != event.getTo().getZ()){
               event.setCancelled(true);
           }
       }
   }
}
