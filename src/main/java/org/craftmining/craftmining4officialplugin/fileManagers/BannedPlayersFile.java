package org.craftmining.craftmining4officialplugin.fileManagers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BannedPlayersFile {
    private static File file;
    private static FileConfiguration config;

    public static void setup(){
        file = new File(
                Bukkit.getServer().getPluginManager().getPlugin("CraftMining4OfficialPlugin").getDataFolder(),
                "bannedPlayersFile.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration getConfig() {
        return config;
    }
    public static void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}
