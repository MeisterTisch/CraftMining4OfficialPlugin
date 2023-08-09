package org.craftmining.craftmining4officialplugin.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.craftmining.craftmining4officialplugin.CraftMining4OfficialPlugin;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.newPlayers.Intros;

public class StartSeasonCommand implements CommandExecutor {
    private final CraftMining4OfficialPlugin plugin;

    public StartSeasonCommand(CraftMining4OfficialPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("CraftMining4OfficialPlugin.startSeason")){
                if(args.length == 0){
                    plugin.getConfig().set("hasSeasonBegun", true);
                    for(Player player1 : Bukkit.getOnlinePlayers()){
                        if(PlayerManagerFile.getConfig().getBoolean(player1.getDisplayName()+".hasAcceptedRules"))
                            Intros.showFirstTimeIntro(player1);
                    }
                } else player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                + ChatColor.GOLD + "/start");
            } else player.sendMessage(ChatColor.RED + "Diesen Command darfst du nicht ausführen!");
        } else sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }
}
