package org.craftmining.craftmining4officialplugin.admin.invSee;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EndseeCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    player.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht online!");
                    return true;
                }
                if(target == player){
                    player.sendMessage(ChatColor.RED + "Du kannst diesen Command nicht auf dich selbst ausführen!");
                    return true;
                }
                player.openInventory(target.getEnderChest());
            } else
                player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                        + ChatColor.GOLD + "/endsee <player>");
        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1)
            return null;
        else return new ArrayList<>();
    }
}
