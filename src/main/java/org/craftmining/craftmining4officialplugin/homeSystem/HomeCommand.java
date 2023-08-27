package org.craftmining.craftmining4officialplugin.homeSystem;

import org.bukkit.ChatColor;
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
            list.add("share");
            list.add("tp");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "delete", "rename", "share", "tp" -> {
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