package org.craftmining.craftmining4officialplugin.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamMessageCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            String playerName = player.getDisplayName();
            String team = null;
            for(String teamName : TeamsFile.getConfig().getStringList("teamsList")){
                if(TeamsFile.getConfig().getStringList(teamName+".playersList").contains(playerName)){
                    team = teamName;
                    break;
                }
            }

            if(team != null){
                StringBuilder message = new StringBuilder();
                for(String string : args)
                    message.append(string);

                for(Player onlinePlayer : Bukkit.getOnlinePlayers())
                    if(TeamsFile.getConfig().getStringList(team+".playersList").contains(onlinePlayer.getDisplayName())
                    && onlinePlayer != player)
                        onlinePlayer.sendMessage(ChatColor.BLUE + "Message an das Team von " + ChatColor.GREEN + player.getDisplayName() + ChatColor.BLUE + ":\n" +
                                ChatColor.GRAY + message);
            } else
                player.sendMessage(ChatColor.RED + "Du bist in keinem Team!");
        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
