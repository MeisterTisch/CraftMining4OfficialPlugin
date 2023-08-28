package org.craftmining.craftmining4officialplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class FakeOpCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht auf dem Server!");
                return true;
            }
            sender.sendMessage(ChatColor.BLUE + "Fakeop-Nachricht wurde an " + ChatColor.GREEN + args[0] + ChatColor.BLUE + " versendet.");
            if(sender instanceof Player player)
                target.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[" + player.getDisplayName() + ": Made " + target.getDisplayName() + " a server operator]");
            else
                target.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "[Server: Made " + target.getDisplayName() + " a server operator]");
        } else
            sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                    + ChatColor.GOLD + "/fakeop <player>");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
