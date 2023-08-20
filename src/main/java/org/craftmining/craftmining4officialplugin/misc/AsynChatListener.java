package org.craftmining.craftmining4officialplugin.misc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.teams.TeamsFile;

public class AsynChatListener implements Listener {
    @EventHandler
    public void messageSent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (PlayerManagerFile.getConfig().getBoolean(player.getDisplayName() + ".hasAcceptedRules")) {
            boolean isInTeam = false;
            for (String team : TeamsFile.getConfig().getStringList("teamsList")) {
                if (TeamsFile.getConfig().getStringList(team + ".playersList").contains(player.getDisplayName())) {
                    ChatColor color = ChatColor.valueOf(TeamsFile.getConfig().getString(team + ".color"));
                    String tag = TeamsFile.getConfig().getString(team + ".displayname");
                    isInTeam = true;
                    event.setMessage(color + "" + ChatColor.BOLD + "[" + tag + "] " + ChatColor.RESET + player.getDisplayName() + ": " + event.getMessage());
                    break;
                }
            }
            if (!isInTeam)
                event.setMessage(ChatColor.RESET + player.getDisplayName() + ": " + event.getMessage());
        } else {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Bitte akzeptiere die Regeln zuerst!");
        }
        event.setFormat(event.getMessage());
    }
}
