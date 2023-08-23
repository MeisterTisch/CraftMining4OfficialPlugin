package org.craftmining.craftmining4officialplugin.chestProtect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ProtectCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("CraftMining4OfficialPlugin.protectChest")){
                Block chest = player.getTargetBlock(null, 5);

                if(chest.getType() != Material.CHEST){

                } else
                    player.sendMessage(ChatColor.RED + "Das ist keine Truhe!");
            } else
                sender.sendMessage(ChatColor.RED + "Du hast keine Rechte für diesen Command!");
        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
