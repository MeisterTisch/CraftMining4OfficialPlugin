package org.craftmining.craftmining4officialplugin.admin.listening;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;

import java.util.ArrayList;
import java.util.List;

public class TravelListener implements Listener {
    @EventHandler
    public void playerTraveledEvent(PlayerChangedWorldEvent event){
        PlayerManagerFile.reload();
        List<Player> listeningPlayersList = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerManagerFile.getConfig().getBoolean(player.getDisplayName() + ".listener.travel.isListening")) {
                listeningPlayersList.add(player);
            }
        }

        for (Player player : listeningPlayersList) {
            if(PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.travel.typeOfListening").equalsIgnoreCase("on")
            && event.getPlayer() != player){
                player.sendMessage(ChatColor.GREEN + event.getPlayer().getDisplayName() + ChatColor.BLUE + " ist von " +
                        ChatColor.GREEN + event.getFrom().getName() + ChatColor.BLUE + " zu " +
                        ChatColor.GREEN + event.getPlayer().getWorld().getName() + ChatColor.BLUE + " getravelt.");
            } else if(PlayerManagerFile.getConfig().getString(player.getDisplayName()+".listener.travel.typeOfListening").equalsIgnoreCase("worlds")
                    && event.getPlayer() != player){
                for(String world : PlayerManagerFile.getConfig().getStringList(player.getDisplayName()+".listener.travel.worldsList")){
                    if(world.equalsIgnoreCase(event.getFrom().getName())){
                        player.sendMessage(ChatColor.GREEN + event.getPlayer().getDisplayName() + ChatColor.BLUE + " travelte von " +
                                ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getFrom().getName() + ChatColor.BLUE + " nach " +
                                ChatColor.GREEN + event.getPlayer().getWorld().getName());
                    } else if(world.equalsIgnoreCase(event.getPlayer().getWorld().getName())){
                        player.sendMessage(ChatColor.GREEN + event.getPlayer().getDisplayName() + ChatColor.BLUE + " travelte von " +
                                ChatColor.GREEN + event.getFrom().getName() + ChatColor.BLUE + " nach " +
                                ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getPlayer().getWorld().getName());
                    }
                }
            } else if(PlayerManagerFile.getConfig().getString(player.getDisplayName()+".listener.travel.typeOfListening").equalsIgnoreCase("players")
            && event.getPlayer() != player){
                for(String playerList : PlayerManagerFile.getConfig().getStringList(player.getDisplayName()+".listener.travel.playersList")){
                    if(Bukkit.getPlayer(playerList) == event.getPlayer()){
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getPlayer().getDisplayName() + ChatColor.BLUE + " travelte von " +
                                ChatColor.GREEN + event.getFrom().getName() + ChatColor.BLUE + " nach " +
                                ChatColor.GREEN +  event.getPlayer().getWorld().getName());
                    }
                }
            }
        }
    }
}
