package org.craftmining.craftmining4officialplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.craftmining.craftmining4officialplugin.elytraBegin.FlyHighCommand;
import org.craftmining.craftmining4officialplugin.elytraBegin.PlayerLandedEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.BannedPlayersFile;
import org.craftmining.craftmining4officialplugin.fileManagers.MutedPlayersFile;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;

public final class CraftMining4OfficialPlugin extends JavaPlugin {
    PluginManager pluginManager;
    private final CraftMining4OfficialPlugin plugin = this;

    @Override
    public void onEnable() {
        pluginManager = Bukkit.getPluginManager();

        this.saveDefaultConfig();

        PlayerManagerFile.setup();
        PlayerManagerFile.getConfig().options().copyDefaults(true);
        PlayerManagerFile.saveConfig();

        BannedPlayersFile.setup();
        BannedPlayersFile.getConfig().options().copyDefaults(true);
        BannedPlayersFile.saveConfig();

        MutedPlayersFile.setup();
        MutedPlayersFile.getConfig().options().copyDefaults(true);
        MutedPlayersFile.saveConfig();

        checkIfSeasonHasBegun();

        //LISTENERS
        pluginManager.registerEvents(new NoShitDoingWhenSeasonHasNotBegun(this), this);
        pluginManager.registerEvents(new PlayerLandedEvent(), this);

        //COMMANDS
        getCommand("flyhigh").setExecutor(new FlyHighCommand(plugin));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkIfSeasonHasBegun(){
        if(getConfig().getBoolean("hasSeasonBegun")){
            //Season has begun
            Bukkit.getWorld("world").getWorldBorder().setSize(300000);

        } else {
            //Season hasn't begun yet
            Bukkit.getWorld("world").getWorldBorder().setCenter(-492.5, -63.5);
            Bukkit.getWorld("world").getWorldBorder().setSize(10);
            for(Player player : Bukkit.getOnlinePlayers()) player.teleport(new Location(Bukkit.getWorld("world"), -492.5, 151.1, -63.5, 0,0));
        }
    }
}
