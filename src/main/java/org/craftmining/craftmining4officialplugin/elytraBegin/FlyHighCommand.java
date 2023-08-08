package org.craftmining.craftmining4officialplugin.elytraBegin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.craftmining.craftmining4officialplugin.CraftMining4OfficialPlugin;

public class FlyHighCommand implements CommandExecutor {
    private static CraftMining4OfficialPlugin plugin;

    public FlyHighCommand(CraftMining4OfficialPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("CraftMining4OfficialPlugin.FlyHighCommand")){
                if(args.length == 0){
                    letItFly(player);
                } else player.sendMessage(ChatColor.RED + "Bitte führe den Command so aus: \n"
                + ChatColor.GOLD + "/flyhigh");
            } else player.sendMessage(ChatColor.RED + "Du hast nicht genügend Rechte um diesen Command auszuführen!");
        } else sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }

    public static void letItFly(Player player){
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta elytraMeta = elytra.getItemMeta();
        elytraMeta.setLocalizedName("Temporärer Elytra");
        elytra.setItemMeta(elytraMeta);
        player.getInventory().setChestplate(elytra);

        player.setVelocity(new Vector(0,15,0));

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> player.setVelocity(player.getEyeLocation().getDirection().multiply(2.5)), 25);
    }
}
