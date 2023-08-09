package org.craftmining.craftmining4officialplugin;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.craftmining.craftmining4officialplugin.admin.StartSeasonCommand;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.FlyHighCommand;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.PlayerLandedEvent;
import org.craftmining.craftmining4officialplugin.fileManagers.BannedPlayersFile;
import org.craftmining.craftmining4officialplugin.fileManagers.MutedPlayersFile;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.fun.PfuCommand;
import org.craftmining.craftmining4officialplugin.misc.AnnouncingAchievements;
import org.craftmining.craftmining4officialplugin.newPlayers.rules.JoinListener;
import org.craftmining.craftmining4officialplugin.newPlayers.rules.RulesCommand;
import org.craftmining.craftmining4officialplugin.newPlayers.rules.MoveListener;

public final class CraftMining4OfficialPlugin extends JavaPlugin {
    PluginManager pluginManager;

    @Override
    public void onEnable() {
        pluginManager = Bukkit.getPluginManager();

        for(World world : Bukkit.getWorlds()){
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }

        this.saveDefaultConfig();

        PlayerManagerFile.setup();
//        PlayerManagerFile.getConfig().options().copyDefaults(true);
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
        pluginManager.registerEvents(new AnnouncingAchievements(), this);
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new JoinListener(this), this);

        //COMMANDS
        getCommand("flyhigh").setExecutor(new FlyHighCommand(this));
        getCommand("pfu").setExecutor(new PfuCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("start").setExecutor(new StartSeasonCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkIfSeasonHasBegun(){
        if(getConfig().getBoolean("hasSeasonBegun")){
            //Season has begun
            Bukkit.getWorlds().get(0).getWorldBorder().setSize(300000);

        } else {
            //Season hasn't begun yet
            Bukkit.getWorlds().get(0).getWorldBorder().setCenter(-492.5, -63.5);
            Bukkit.getWorlds().get(0).getWorldBorder().setSize(9);
            for(Player player : Bukkit.getOnlinePlayers()) player.teleport(new Location(Bukkit.getWorlds().get(0), -492.5, 151.0, -63.5, 0,0));

            for(int i = 0; i <= 4; i++){
                for (int x = -493-i; x <= -493+i; x++) {
                    for (int z = -64-i; z <= -64+i; z++) {
                        Bukkit.getWorlds().get(0).getBlockAt(x, 146+i, z).setType(Material.STONE);
                    }
                }
            }
        }
    }
}
