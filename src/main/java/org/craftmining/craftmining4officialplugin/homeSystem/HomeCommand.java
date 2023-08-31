package org.craftmining.craftmining4officialplugin.homeSystem;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            String name = player.getDisplayName();
            if(args.length != 0){
                switch (args[0]){
                    case "list" -> {
                        if(args.length == 1){
                            StringBuilder string = new StringBuilder();
                            if(!HomesFile.getConfig().getStringList(name + ".homesList").isEmpty()){
                                string.append(ChatColor.BLUE + "Hier ist die Liste deiner gespeicherten Homes:\n");
                                for (int i = 1; i <= HomesFile.getConfig().getStringList(name + ".homesList").size(); i++) {
                                    if (i == HomesFile.getConfig().getStringList(name + ".homesList").size())
                                        string.append(ChatColor.GREEN + HomesFile.getConfig().getStringList(name + ".homesList").get(i-1) + ChatColor.GRAY + ".");
                                    else
                                        string.append(ChatColor.GREEN + HomesFile.getConfig().getStringList(name + ".homesList").get(i-1) + ChatColor.GRAY + ", ");
                                }
                            } else
                                string.append(ChatColor.RED + "Du hast keine gespeicherten Homes!");
                            player.sendMessage(string.toString());
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/home list");
                    }
                    case "tp" -> {
                        if(args.length == 1){
                            StringBuilder string = new StringBuilder();
                            if(!HomesFile.getConfig().getStringList(name + ".homesList").isEmpty()){
                                string.append(ChatColor.RED + "Bitte gib eine deiner Homes an:\n");
                                for (int i = 1; i <= HomesFile.getConfig().getStringList(name + ".homesList").size(); i++) {
                                    if (i == HomesFile.getConfig().getStringList(name + ".homesList").size())
                                        string.append(ChatColor.GOLD + HomesFile.getConfig().getStringList(name + ".homesList").get(i-1) + ChatColor.GRAY + ".");
                                    else
                                        string.append(ChatColor.GOLD + HomesFile.getConfig().getStringList(name + ".homesList").get(i-1) + ChatColor.GRAY + ", ");
                                }
                            } else
                                string.append(ChatColor.RED + "Du hast keine gespeicherten Homes!");
                            player.sendMessage(string.toString());
                        } else if(args.length == 2){
                            List<String> homes = HomesFile.getConfig().getStringList(name + ".homesList");
                            if(!homes.isEmpty()){
                                if(homes.contains(args[1])){
                                    player.teleport(HomesFile.getConfig().getLocation(name + "." + args[1]));
                                    player.sendMessage(ChatColor.BLUE + "Du wurdest zu deinem Home " + ChatColor.GREEN + args[1] + ChatColor.BLUE + " teleportiert.");
                                } else
                                    player.sendMessage(ChatColor.RED + "Du hast kein Home mit dem Namen " + ChatColor.GOLD + args[1] + ChatColor.RED + ".");
                            } else
                                player.sendMessage(ChatColor.RED + "Du hast keine gespeicherten Homes!");

                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/home tp <home>");
                    }
                    case "create" -> {
                        if(args.length == 2){
                            List<String> list = HomesFile.getConfig().getStringList(name + ".homesList");
                            if(list.size() < 5){
                                if(!list.contains(args[1])){
                                    Location loc = player.getLocation();
                                    String locName = args[1];
                                    list.add(args[1]);
                                    HomesFile.getConfig().set(name + ".homesList", list);
                                    HomesFile.getConfig().set(name + "." + args[1], loc);
                                    HomesFile.saveConfig();
                                    player.sendMessage(ChatColor.BLUE + "Dein Home " + ChatColor.GREEN + locName + ChatColor.BLUE + " wurde gespeichert!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Du hast schon ein Home mit dem Namen " + ChatColor.GOLD + args[1] + ChatColor.RED + "!");
                            } else
                                player.sendMessage(ChatColor.RED + "Du hast das Maximum an Homes erreicht! Bitte lösche ein anderes zuerst!");
                        } else if(args.length == 1)
                            player.sendMessage(ChatColor.RED + "Bitte gib einen Namen an!");
                        else if(args.length > 2)
                            player.sendMessage(ChatColor.RED + "Bitte nutze für den Namen keine Leerzeichen! Nutze statt dessen '_' oder '-'.");
                        else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/home create <name>");
                    }
                    case "delete" -> {
                        List<String> list = HomesFile.getConfig().getStringList(name + ".homesList");

                        if(args.length == 1){
                            StringBuilder string = new StringBuilder();
                            if(!HomesFile.getConfig().getStringList(name + ".homesList").isEmpty()){
                                string.append(ChatColor.RED + "Bitte gib eine deiner Homes an:\n");
                                for (int i = 1; i <= HomesFile.getConfig().getStringList(name + ".homesList").size(); i++) {
                                    if (i == HomesFile.getConfig().getStringList(name + ".homesList").size())
                                        string.append(ChatColor.GOLD + HomesFile.getConfig().getStringList(name + ".homesList").get(i-1) + ChatColor.GRAY + ".");
                                    else
                                        string.append(ChatColor.GOLD + HomesFile.getConfig().getStringList(name + ".homesList").get(i-1) + ChatColor.GRAY + ", ");
                                }
                            } else
                                string.append(ChatColor.RED + "Du hast keine gespeicherten Homes!");
                            player.sendMessage(string.toString());
                        } else if(args.length == 2){
                            if(list.contains(args[1])){
                                player.sendMessage(ChatColor.RED + "Willst du wirklich " + ChatColor.GOLD + args[1] + " löschen?\n"
                                       + ChatColor.RED + "Dann schreib: " + ChatColor.GOLD + "/home delete " + args[1] + " confirm");
                            } else
                                player.sendMessage(ChatColor.RED + "Du hast kein Home mit dem Namen " + ChatColor.GOLD + args[1] + ChatColor.RED + "!");
                        } else if(args.length == 3){
                            if(list.contains(args[1])){
                                if(args[2].equals("confirm")){
                                    list.remove(args[1]);
                                    HomesFile.getConfig().set(name+".homesList", list);
                                    HomesFile.getConfig().set(name+"."+args[1], null);
                                    HomesFile.saveConfig();
                                    player.sendMessage(ChatColor.RED + "Dein Home " + ChatColor.GOLD + args[1] + ChatColor.RED + " wurde gelöscht!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte schreibe 'confirm' richtig!");
                            } else
                                player.sendMessage(ChatColor.RED + "Du hast kein Home mit dem Namen " + ChatColor.GOLD + args[1] + ChatColor.RED + "!");
                        }
                    }
                    case "rename" -> {
                        if(args.length == 1)
                            player.sendMessage(ChatColor.RED + "Bitte gib ein Home an!");
                        else if(args.length == 2){
                            if(HomesFile.getConfig().getStringList(name+".homesList").contains(args[1]))
                                player.sendMessage(ChatColor.RED+ "Bitte gib einen neuen Namen ein!");
                            else
                                player.sendMessage(ChatColor.RED + "Du hast kein Home mit dem Namen " + ChatColor.GOLD + args[1] + ChatColor.RED + "!");
                        } else if(args.length == 3){
                            if(HomesFile.getConfig().getStringList(name+".homesList").contains(args[1])){
                                if (!args[1].equals(args[2])) {
                                    List<String> list = HomesFile.getConfig().getStringList(name + ".homesList");
                                    list.remove(args[1]);
                                    list.add(args[2]);
                                    HomesFile.getConfig().set(name + ".homesList", list);
                                    HomesFile.getConfig().set(name + "." + args[2], HomesFile.getConfig().getLocation(name + "." + args[1]));
                                    HomesFile.getConfig().set(name + "." + args[1], null);
                                    HomesFile.saveConfig();
                                    player.sendMessage(ChatColor.BLUE + "Der Name wurde auf " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " geändert.");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte gib nicht den selben Namen an!");
                            } else
                                player.sendMessage(ChatColor.RED + "Du hast kein Home mit dem Namen " + ChatColor.GOLD + args[1] + ChatColor.RED + "!");

                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze für den Namen keine Leerzeichen! Nutze statt dessen '_' oder '-'.");
                    }
                    default -> {
                        player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                + ChatColor.GOLD + "/home <tp/list/create/delete/rename> ...");
                    }
                }
            } else
                player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                        + ChatColor.GOLD + "/home <tp/list/create/delete/rename> ...");
        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("list");
            list.add("create");
            list.add("delete");
            list.add("rename");
            list.add("tp");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "delete", "rename", "tp" -> {
                    if (sender instanceof Player player)
                        list.addAll(HomesFile.getConfig().getStringList(player.getDisplayName() + ".homesList"));
                }
                case "list", "create" -> {
                    return new ArrayList<>();
                }
            }
        } else if (args.length == 3) {
            switch (args[0]) {
                case "share" -> {
                    return null;
                }
                case "delete" -> {
                    list.add("confirm");
                }
            }
        }

        return list;
    }
}