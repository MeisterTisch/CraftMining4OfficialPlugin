package org.craftmining.craftmining4officialplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class CraftMining4OfficialPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerManagerFile.setup();
        PlayerManagerFile.getConfig().options().copyDefaults(true);
        PlayerManagerFile.saveConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
