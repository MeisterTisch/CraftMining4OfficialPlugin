package org.craftmining.craftmining4officialplugin.newPlayers;

import org.bukkit.entity.Player;
import org.craftmining.craftmining4officialplugin.newPlayers.elytraBegin.FlyHighCommand;

public class Intros {
    public static void showFirstTimeIntro(Player player){
        player.sendMessage("LONG INTRO");
        FlyHighCommand.letItFly(player);
    }
    public static void showShortIntro(Player player){
        player.sendMessage("SHORT INTRO");
    }
}
