package org.craftmining.craftmining4officialplugin.fun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class PfuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(args.length == 0){
                player.getWorld().spawn(player.getEyeLocation(), LlamaSpit.class,
                        llamaSpit -> llamaSpit.setVelocity(player.getEyeLocation().getDirection().multiply(0.75)));
            } else player.sendMessage(ChatColor.RED + "Bitte nutze folgenden Command so: \n"
                                    + ChatColor.GOLD + "/pfu");
        } else sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }
}
