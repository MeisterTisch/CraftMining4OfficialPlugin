package org.craftmining.craftmining4officialplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;

public class JoinListener implements Listener {
    private final CraftMining4OfficialPlugin plugin;

    public JoinListener(CraftMining4OfficialPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoinsServer(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!plugin.getConfig().getBoolean("hasSeasonBegun")){
            player.teleport(new Location(Bukkit.getWorld("world"), -492.5, 151.1, -63.5, 0,0));


        } else {
            if(!PlayerManagerFile.getConfig().contains(event.getPlayer().getDisplayName())){

            }
        }
    }
}
