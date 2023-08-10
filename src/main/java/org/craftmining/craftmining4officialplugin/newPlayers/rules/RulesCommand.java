package org.craftmining.craftmining4officialplugin.newPlayers.rules;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;

import java.util.ArrayList;
import java.util.List;

public class RulesCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            showRules(sender);
        } else if(args.length == 1){
            if(args[0].equalsIgnoreCase("accept")){
                if(sender instanceof Player player) {
                    player.kickPlayer(ChatColor.GREEN + "Du hast die Regeln akzeptiert! Viel Spaß!");
                    PlayerManagerFile.getConfig().set(player.getDisplayName() + ".hasAcceptedRules", true);
                    PlayerManagerFile.saveConfig();
                } else sender.sendMessage(ChatColor.RED + "Regeln akzeptieren können nur Spieler!");
            } else if(args[0].equalsIgnoreCase("decline")){
                if(sender instanceof Player player) {
                    PlayerManagerFile.getConfig().set(player.getDisplayName() + ".hasAcceptedRules", false);
                    PlayerManagerFile.saveConfig();
                    player.kickPlayer(ChatColor.RED + "Um mitzuspielen musst du die Regeln akzeptieren!");
                } else sender.sendMessage(ChatColor.RED + "Regeln ablehnen können nur Spieler!");
            } else sender.sendMessage(ChatColor.RED + "Bitte benutzt den Command so:\n"
                    + ChatColor.GOLD + "/rules [accept/decline]");
        } else sender.sendMessage(ChatColor.RED + "Bitte benutzt den Command so:\n"
        + ChatColor.GOLD + "/rules [accept/decline]");
        return true;
    }

    public static void showRules(CommandSender sender){
        sender.sendMessage(ChatColor.GOLD + "Hier findest du die Regeln: https://shorturl.at/eDETZ (Dieser Link ist sicher und führt zu den Regeln.)\n" +
                ChatColor.GOLD + "Akzeptierst du es? Dann schreibe " + ChatColor.GREEN + "/rules accept" + ChatColor.GOLD + ".");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1){
            list.add("accept");
            list.add("decline");
        }

        return list;
    }
}
