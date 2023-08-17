package org.craftmining.craftmining4officialplugin.admin.ban;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.craftmining.craftmining4officialplugin.fileManagers.BannedPlayersFile;

import java.util.ArrayList;
import java.util.List;

public class BanCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("CraftMining4OfficialPlugin.admin.ban")){
            if (label.equalsIgnoreCase("ban")) {
                if (args.length != 0) {
                    if (args.length == 1) {
                        if (sender instanceof Player player) {
                            if (player.getDisplayName().equalsIgnoreCase(args[0])) {
                                player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht bannen!");
                                return true;
                            }
                        }
                        if (BannedPlayersFile.getConfig().contains(args[0]))
                            sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist schon gebannt!\n" +
                                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(args[0] + ".reason"));
                        else {
                            BannedPlayersFile.getConfig().set(args[0] + ".reason", ChatColor.RED + "Der Banhammer hat gesprochen!");
                            BannedPlayersFile.getConfig().set(args[0] + ".duration", "unlimited");
                            BannedPlayersFile.saveConfig();
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "Du wurdest gebannt!\n" +
                                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(Bukkit.getPlayer(args[0]).getDisplayName() + ".reason") + "\n" +
                                    ChatColor.RED + "Dauer: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(Bukkit.getPlayer(args[0]).getDisplayName() + ".duration"));
                        }
                    } else if(args.length > 1) {
                        if (sender instanceof Player player) {
                            if (player.getDisplayName().equalsIgnoreCase(args[0])) {
                                player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht bannen!");
                                return true;
                            }
                        }
                        StringBuilder reason = new StringBuilder();
                        for(String string : args){
                            if(!string.equals(args[0])) reason.append(string + " ");
                        }
                        if (BannedPlayersFile.getConfig().contains(args[0]))
                            sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist schon gebannt!\n" +
                                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(args[0] + ".reason"));
                        else {
                            BannedPlayersFile.getConfig().set(args[0] + ".reason", ChatColor.RED + "" + reason);
                            BannedPlayersFile.getConfig().set(args[0] + ".duration", "unlimited");
                            BannedPlayersFile.saveConfig();
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "Du wurdest gebannt!\n" +
                                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(Bukkit.getPlayer(args[0]).getDisplayName() + ".reason") + "\n" +
                                    ChatColor.RED + "Dauer: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(Bukkit.getPlayer(args[0]).getDisplayName() + ".duration"));
                        }
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                                ChatColor.GOLD + " /ban <Spieler> [Grund]");
                } else
                    sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                            ChatColor.GOLD + " /ban <Spieler> [Grund]");
            } else if (label.equalsIgnoreCase("tempban")) {
                if (args.length != 0) {
                    if(args.length == 3){
                        if (sender instanceof Player player) {
                            if (player.getDisplayName().equalsIgnoreCase(args[0])) {
                                player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht bannen!");
                                return true;
                            }
                        }
                        //DAUER EINFÜGEN AUCH OBEN
                        if (BannedPlayersFile.getConfig().contains(args[0]))
                            sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist schon gebannt!\n" +
                                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(args[0] + ".reason"));
                        else {
                            BannedPlayersFile.getConfig().set(args[0] + ".reason", ChatColor.RED + "Der Banhammer hat gesprochen!");
                            BannedPlayersFile.getConfig().set(args[0] + ".duration", "unlimited");
                            BannedPlayersFile.saveConfig();
                            Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.RED + "Du wurdest gebannt!\n" +
                                    ChatColor.RED + "Grund: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(Bukkit.getPlayer(args[0]).getDisplayName() + ".reason") + "\n" +
                                    ChatColor.RED + "Dauer: " + ChatColor.GOLD + BannedPlayersFile.getConfig().getString(Bukkit.getPlayer(args[0]).getDisplayName() + ".duration"));
                        }

                    } else if(args.length > 3){

                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                                ChatColor.GOLD + " /ban <Spieler> <Dauer> <Zeiteinheit> [Grund]");
                } else
                    sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                            ChatColor.GOLD + " /ban <Spieler> <Dauer> <Zeiteinheit> [Grund]");
            } else if (label.equalsIgnoreCase("unban")) {

            } else
                sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                        ChatColor.GOLD + "/(temp)ban ... " + ChatColor.RED + "oder" + ChatColor.GOLD + " /unban ...");
        } else sender.sendMessage(ChatColor.RED + "Du hast keine Rechte dafür!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(label.equalsIgnoreCase("ban")){
            if(args.length == 1) return null;
        } else if(label.equalsIgnoreCase("tempban")){
            if(args.length == 1) return null;
            else if(args.length == 2){
                list.add("1");
                list.add("5");
                list.add("10");
                list.add("12");
                list.add("24");
            } else if(args.length == 3){
                list.add("sec");
                list.add("min");
                list.add("hour");
                list.add("day");
                list.add("week");
                list.add("month");
            }
        }

        return list;
    }
}
