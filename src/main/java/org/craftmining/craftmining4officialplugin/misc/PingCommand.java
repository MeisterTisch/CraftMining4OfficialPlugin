package org.craftmining.craftmining4officialplugin.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length == 0){
                if(Math.random() < 0.1){
                    player.sendMessage(ChatColor.BLUE + "Dein Ping zum Server liegt bei " +
                            ChatColor.GREEN + player.getPing() + ChatColor.BLUE + " ms.");
                } else player.sendMessage(ChatColor.BLUE + "P" + ChatColor.GREEN + "O"
                + ChatColor.BLUE + "N" + ChatColor.BLUE + "G" + ChatColor.GREEN + "!");
            } else if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    player.sendMessage(ChatColor.GREEN + target.getDisplayName() + "'s " + ChatColor.BLUE + "Ping zum Server liegt bei " +
                            ChatColor.GREEN + target.getPing() + ChatColor.BLUE + " ms.");
                } else {
                    player.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht auf diesem Server.");
                }
            } else player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                    ChatColor.GOLD + "/ping [Spieler]");
        } else {
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    sender.sendMessage(ChatColor.GREEN + target.getDisplayName() + "'s " + ChatColor.BLUE + "Ping zum Server liegt bei " +
                            ChatColor.GREEN + target.getPing() + ChatColor.BLUE + " ms.");
                } else {
                    sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht auf diesem Server.");
                }
            } else sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                    ChatColor.GOLD + "/ping <Spieler>");
        }
        return true;
    }
}
