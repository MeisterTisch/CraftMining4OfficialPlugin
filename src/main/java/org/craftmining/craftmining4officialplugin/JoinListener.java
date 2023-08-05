package org.craftmining.craftmining4officialplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final CraftMining4OfficialPlugin plugin;

    public JoinListener(CraftMining4OfficialPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoinsServer(PlayerJoinEvent event){
        if(!PlayerManagerFile.getConfig().contains(event.getPlayer().getDisplayName())){

        }
    }
}
