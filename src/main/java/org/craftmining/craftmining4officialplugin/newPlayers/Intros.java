package org.craftmining.craftmining4officialplugin.newPlayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.craftmining.craftmining4officialplugin.CraftMining4OfficialPlugin;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.FlyHighCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Intros {
    private static final List<Player> playerList = new ArrayList<>();
    private static final CraftMining4OfficialPlugin plugin = CraftMining4OfficialPlugin.getPlugin();
    private ScheduledExecutorService service;
    private ScheduledFuture scheduledFuture;
    int i = 0;

    public Intros(){

    }

    public void showFirstTimeIntro(Player player){
        service = Executors.newSingleThreadScheduledExecutor();
        playerList.add(player);

        scheduledFuture = service.scheduleAtFixedRate(() -> {
            if(i == 0){
                Bukkit.getScheduler().runTask(plugin, () -> player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 640, 255)));
                player.sendTitle(ChatColor.GREEN + "Es ist wieder Zeit...", "", 40, 50, 20);
            } else if(i == 40+50+20){
                player.sendTitle(ChatColor.GREEN + "Craft Mining...", ChatColor.BLUE + "...geht in die nächste Season.", 40, 50, 20);
            } else if(i == (40+50+20)*2){
                player.sendTitle(ChatColor.GREEN + "Seit ihr bereit...", ChatColor.BLUE + "...für die vierte Staffel?", 40, 50, 20);
            } else if(i == (40+50+20)*3){
                player.sendTitle(ChatColor.GREEN + "Die Elytra verschwindet...", ChatColor.BLUE + "...sobald du gelandet bist!", 40, 50, 20);
            } else if(i == (40+50+20)*4){
                player.sendTitle(ChatColor.GREEN + "Also pass auf...", ChatColor.BLUE + "...wenn du fliegst.", 40, 50, 20);
            } else if(i == (40+50+20)*5){
                player.sendTitle(ChatColor.RED + "5", "", 5, 10, 5);
            } else if(i == (40+50+20)*5+20){
                player.sendTitle(ChatColor.RED + "4", "", 5, 10, 5);
            } else if(i == (40+50+20)*5+20*2){
                player.sendTitle(ChatColor.YELLOW + "3", "", 5, 10, 5);
            } else if(i == (40+50+20)*5+20*3){
                player.sendTitle(ChatColor.YELLOW + "2", "", 5, 10, 5);
            } else if(i == (40+50+20)*5+20*4){
                player.sendTitle(ChatColor.GREEN + "1", "", 5, 10, 5);
            } else if(i == (40+50+20)*5+20*5){
                player.sendTitle(ChatColor.GREEN + "START!", "", 5, 10, 25);
                playerList.remove(player);
                FlyHighCommand.letItFly(player);
                PlayerManagerFile.getConfig().set(player.getDisplayName()+".gotFirstTimeIntro", true);
                PlayerManagerFile.saveConfig();
            } else if(i == (40+50+20)*5+20*6){
                scheduledFuture.cancel(true);
            }
            i++;
        }, 0, 50, TimeUnit.MILLISECONDS);
    }
    public static void showShortIntro(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*2, 255));
        player.sendTitle(ChatColor.BLUE + "Willkommen zurück!", ChatColor.GREEN + "Viel Spaß bei Craft Mining 4!", 15, 40,5);
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }
}
