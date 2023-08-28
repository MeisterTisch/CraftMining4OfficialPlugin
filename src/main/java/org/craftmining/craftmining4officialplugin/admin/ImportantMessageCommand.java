package org.craftmining.craftmining4officialplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ImportantMessageCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 0){
            StringBuilder message = new StringBuilder();
            for (String string : args) {
                message.append(string + " ");
            }

            String autor = "Konsole";
            if (sender instanceof Player player)
                autor = player.getDisplayName();

            Bukkit.broadcastMessage(ChatColor.GRAY + ">>>\n" +
                    ChatColor.RED + "" + ChatColor.BOLD + "Wichtige Nachricht von " + ChatColor.GOLD + "" + ChatColor.BOLD + autor + ":\n" +
                    ChatColor.GOLD + message + "\n" +
                    ChatColor.GRAY + ">>>");
        } else
            sender.sendMessage(ChatColor.RED + "Bitte gib eine Nachricht an!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
