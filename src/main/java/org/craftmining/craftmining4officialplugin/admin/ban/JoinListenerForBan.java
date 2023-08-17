package org.craftmining.craftmining4officialplugin.admin.ban;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.BannedPlayersFile;

public class JoinListenerForBan implements Listener {
    @EventHandler
    public void playerTriesToJoin(AsyncPlayerPreLoginEvent event){
        if(BannedPlayersFile.getConfig().contains(event.getName())){
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
            event.setKickMessage(ChatColor.RED + "Du bist gebannt!\n" +
                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(event.getName()+".reason") + "\n" +
                    ChatColor.RED + "Dauer: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(event.getName()+".duration"));
        } else event.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
    }
}
