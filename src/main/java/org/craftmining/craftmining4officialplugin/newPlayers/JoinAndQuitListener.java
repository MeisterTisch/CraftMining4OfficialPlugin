package org.craftmining.craftmining4officialplugin.newPlayers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;

public class JoinAndQuitListener implements Listener {
    @EventHandler
    public void playerJoinedListener(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String name = player.getDisplayName();

        player.setPlayerListHeaderFooter(
                ChatColor.BLUE + "----------\n" + ChatColor.BLUE + "Craft Mining" + ChatColor.GREEN + "Season 4",
                ChatColor.GRAY + ">>> Willkommen zurück, " + name + "! <<<\n" + ChatColor.GREEN + "----------");


        if(!PlayerManagerFile.getConfig().getBoolean(name + ".hasAcceptedRules")) event.setJoinMessage("");
        else if(!PlayerManagerFile.getConfig().getBoolean(name + ".sentFirstTimeMessage")) {
            event.setJoinMessage(ChatColor.GRAY + ">>>\n" +
                    ChatColor.GREEN + name + ChatColor.BLUE + " spielt zum ersten Mal auf dem Server!\n" +
                    ChatColor.BLUE + "Herzlich Willkommen!\n" +
                    ChatColor.GRAY + ">>>");
            PlayerManagerFile.getConfig().set(name+".sentFirstTimeMessage", true);
            PlayerManagerFile.saveConfig();
        }
        else event.setJoinMessage(ChatColor.GREEN + name + ChatColor.BLUE + " ist wieder da!");
    }

    @EventHandler
    public void playerQuitListener(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String name = player.getDisplayName();

        if(!PlayerManagerFile.getConfig().getBoolean(name + ".hasAcceptedRules")) event.setQuitMessage("");
        else event.setQuitMessage(ChatColor.GREEN + name + ChatColor.BLUE + " ist nun weg!");
    }
}
