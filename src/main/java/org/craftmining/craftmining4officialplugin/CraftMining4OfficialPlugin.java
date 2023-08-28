package org.craftmining.craftmining4officialplugin;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.craftmining.craftmining4officialplugin.admin.ImportantMessageCommand;
import org.craftmining.craftmining4officialplugin.admin.StartSeasonCommand;
import org.craftmining.craftmining4officialplugin.admin.TestCommand;
import org.craftmining.craftmining4officialplugin.admin.listening.ListenForEventsCommand;
import org.craftmining.craftmining4officialplugin.admin.listening.MessageListener;
import org.craftmining.craftmining4officialplugin.admin.listening.TravelListener;
import org.craftmining.craftmining4officialplugin.chestProtect.ProtectedChestsFile;
import org.craftmining.craftmining4officialplugin.fileManagers.BannedPlayersFile;
import org.craftmining.craftmining4officialplugin.fileManagers.MutedPlayersFile;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.fun.PfuCommand;
import org.craftmining.craftmining4officialplugin.homeSystem.HomeCommand;
import org.craftmining.craftmining4officialplugin.homeSystem.HomesFile;
import org.craftmining.craftmining4officialplugin.misc.AnnouncingAchievements;
import org.craftmining.craftmining4officialplugin.misc.AsynChatListener;
import org.craftmining.craftmining4officialplugin.misc.NoShitDoingAtSpawn;
import org.craftmining.craftmining4officialplugin.misc.PingCommand;
import org.craftmining.craftmining4officialplugin.msgSystem.MsgCommand;
import org.craftmining.craftmining4officialplugin.newPlayers.JoinAndQuitListener;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.FlyHighCommand;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.PlayerInventoryMoveListener;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.PlayerLandedEvent;
import org.craftmining.craftmining4officialplugin.newPlayers.rules.JoinListenerForRules;
import org.craftmining.craftmining4officialplugin.newPlayers.rules.MoveListener;
import org.craftmining.craftmining4officialplugin.newPlayers.rules.RulesCommand;
import org.craftmining.craftmining4officialplugin.teams.TeamsCommand;
import org.craftmining.craftmining4officialplugin.teams.TeamsFile;
import org.craftmining.craftmining4officialplugin.tpSystem.TPCommand;
import org.craftmining.craftmining4officialplugin.tpSystem.TPFile;

public final class CraftMining4OfficialPlugin extends JavaPlugin {
    PluginManager pluginManager;
    private static CraftMining4OfficialPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        pluginManager = Bukkit.getPluginManager();

        this.saveDefaultConfig();
        PlayerManagerFile.setup();
        PlayerManagerFile.saveConfig();
        BannedPlayersFile.setup();
        BannedPlayersFile.saveConfig();
        MutedPlayersFile.setup();
        MutedPlayersFile.saveConfig();
        TeamsFile.setup();
        TeamsFile.saveConfig();
        ProtectedChestsFile.setup();
        ProtectedChestsFile.saveConfig();
        HomesFile.setup();
        HomesFile.saveConfig();
        TPFile.setup();
        TPFile.saveConfig();
        checkIfSeasonHasBegun();

        for(World world : Bukkit.getWorlds()){
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, true);
        }

        for(Player player : Bukkit.getOnlinePlayers()){
            String name = player.getDisplayName();

            if(!PlayerManagerFile.getConfig().contains(name+".hasJumpedAlready"))
                PlayerManagerFile.getConfig().set(name + ".hasJumpedAlready", false);
            if(!PlayerManagerFile.getConfig().contains(name+".hasAcceptedRules"))
                PlayerManagerFile.getConfig().set(name + ".hasAcceptedRules", false);
            if(!PlayerManagerFile.getConfig().contains(name+".gotFirstTimeIntro"))
                PlayerManagerFile.getConfig().set(name + ".gotFirstTimeIntro", false);
            if(!PlayerManagerFile.getConfig().contains(name+".sentFirstTimeMessage"))
                PlayerManagerFile.getConfig().set(name + ".sentFirstTimeMessage", false);
            PlayerManagerFile.saveConfig();

            if(!PlayerManagerFile.getConfig().getBoolean(player.getDisplayName()+".hasAcceptedRules")){
                player.sendMessage(ChatColor.RED + "Bitte akzeptiere die Regeln!\n" +
                        ChatColor.RED + "Schreibe: " + ChatColor.GOLD + "/rules" + ChatColor.RED + " um diese durchzulesen!");
            }
        }

        //LISTENERS
        pluginManager.registerEvents(new NoShitDoingAtSpawn(), this);
        pluginManager.registerEvents(new PlayerLandedEvent(), this);
        pluginManager.registerEvents(new AnnouncingAchievements(), this);
        pluginManager.registerEvents(new MoveListener(), this);
        pluginManager.registerEvents(new JoinListenerForRules(this), this);
        pluginManager.registerEvents(new JoinAndQuitListener(), this);
        pluginManager.registerEvents(new MessageListener(), this);
        pluginManager.registerEvents(new TravelListener(), this);
        pluginManager.registerEvents(new AsynChatListener(), this);
        pluginManager.registerEvents(new PlayerInventoryMoveListener(), this);

        //COMMANDS
        getCommand("flyhigh").setExecutor(new FlyHighCommand(this));
        getCommand("pfu").setExecutor(new PfuCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("start").setExecutor(new StartSeasonCommand(this));
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("listen").setExecutor(new ListenForEventsCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("teams").setExecutor(new TeamsCommand());
        getCommand("important").setExecutor(new ImportantMessageCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("teleport").setExecutor(new TPCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkIfSeasonHasBegun(){
        if(getConfig().getBoolean("hasSeasonBegun")){
            //Season has begun
            Bukkit.getWorlds().get(0).getWorldBorder().setSize(300000);
            for(World world : Bukkit.getWorlds()){
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                world.setGameRule(GameRule.DO_WEATHER_CYCLE, true);
                world.setDifficulty(Difficulty.NORMAL);
            }

        } else {
            //Season hasn't begun yet
            for(World world : Bukkit.getWorlds()){
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                world.setDifficulty(Difficulty.PEACEFUL);
                world.setFullTime(1000);
            }
            Bukkit.getWorlds().get(0).getWorldBorder().setCenter(-492.5, -63.5);
            Bukkit.getWorlds().get(0).getWorldBorder().setSize(9);
            for(Player player : Bukkit.getOnlinePlayers()) player.teleport(new Location(Bukkit.getWorlds().get(0), -492.5, 151.0, -63.5, 0,0));

            for(int i = 0; i <= 4; i++){
                for (int x = -493-i; x <= -493+i; x++) {
                    for (int z = -64-i; z <= -64+i; z++) {
                        if(Math.random() < 0.5) Bukkit.getWorlds().get(0).getBlockAt(x, 146+i, z).setType(Material.BLUE_CONCRETE);
                        else Bukkit.getWorlds().get(0).getBlockAt(x, 146+i, z).setType(Material.LIME_CONCRETE);
                    }
                }
            }
        }
    }

    public static CraftMining4OfficialPlugin getPlugin() {
        return plugin;
    }
}
