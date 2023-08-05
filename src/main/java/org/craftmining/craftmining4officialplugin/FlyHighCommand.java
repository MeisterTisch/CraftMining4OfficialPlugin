package org.craftmining.craftmining4officialplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FlyHighCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("CraftMining4OfficialPlugin.FlyHighCommand")){
                if(args.length == 0){

                    player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));

                } else player.sendMessage(ChatColor.RED + "Bitte führe den Command so aus: \n"
                + ChatColor.GOLD + "/flyhigh");
            } else player.sendMessage(ChatColor.RED + "Du hast nicht genügend Rechte um diesen Command auszuführen!");
        } else sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }
}
