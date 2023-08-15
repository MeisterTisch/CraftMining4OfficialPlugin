package org.craftmining.craftmining4officialplugin.newPlayers.rules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.craftmining.craftmining4officialplugin.CraftMining4OfficialPlugin;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.newPlayers.Intros;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class JoinListenerForRules implements Listener {
    private final CraftMining4OfficialPlugin plugin;

    public JoinListenerForRules(CraftMining4OfficialPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Player:
     * hasJumpedAlready: boolean
     * hasAcceptedRules: boolean
     * gotFirstTimeIntro: boolean
     * sentFirstTimeMessage: boolean
     *
     */

    int i = 0;
    Player player;
    @EventHandler
    public void playerJoinsServer(PlayerJoinEvent event){
        player = event.getPlayer();
        String name = player.getDisplayName();

        PlayerManagerFile.reload();

        if(!PlayerManagerFile.getConfig().contains(name+".hasJumpedAlready"))
            PlayerManagerFile.getConfig().set(name + ".hasJumpedAlready", false);
        if(!PlayerManagerFile.getConfig().contains(name+".hasAcceptedRules"))
            PlayerManagerFile.getConfig().set(name + ".hasAcceptedRules", false);
        if(!PlayerManagerFile.getConfig().contains(name+".gotFirstTimeIntro"))
            PlayerManagerFile.getConfig().set(name + ".gotFirstTimeIntro", false);
        if(!PlayerManagerFile.getConfig().contains(name+".sentFirstTimeMessage"))
            PlayerManagerFile.getConfig().set(name + ".sentFirstTimeMessage", false);
        PlayerManagerFile.saveConfig();

        if(!PlayerManagerFile.getConfig().getBoolean(name+".hasAcceptedRules")){
            player.teleport(new Location(Bukkit.getWorld("world"), -492.5, 151.0, -63.5, 0, 0));
            player.sendMessage(ChatColor.RED + "Bitte akzeptiere die Regeln!\n" +
                    ChatColor.RED + "Schreibe: " + ChatColor.GOLD + "/rules" + ChatColor.RED + " um diese durchzulesen!");
        } else {
            if(plugin.getConfig().getBoolean("hasSeasonBegun")){
                if(!PlayerManagerFile.getConfig().getBoolean(name + ".hasJumpedAlready")
                && !PlayerManagerFile.getConfig().getBoolean(name + ".gotFirstTimeIntro")){
                    player.teleport(new Location(Bukkit.getWorld("world"), -492.5, 151.0, -63.5, 0, 0));
                    Bukkit.getScheduler().runTask(plugin, () -> new Intros().showFirstTimeIntro(player));
                } else
                    Bukkit.getScheduler().runTask(plugin, () -> Intros.showShortIntro(player));
            } else
                player.teleport(new Location(Bukkit.getWorld("world"), -492.5, 151.0, -63.5, 0, 0));
        }
    }
}