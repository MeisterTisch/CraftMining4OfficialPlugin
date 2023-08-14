package org.craftmining.craftmining4officialplugin.admin.listening;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ListenForEventsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("CraftMining4OfficialPlugin.listenForMessages")){

            } else
                player.sendMessage(ChatColor.RED + "Du hast dazu keine Rechte!");
        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1){
            /**
             * Messages: /msg between players
             * Travel: players when they travel trough nether or end portal.
             * blockplace: players when they place blocks.
             * blockbreak: players when they break blocks.
             *
             */

            list.add("messages");
            list.add("travel");
            list.add("blockplace");
            list.add("blockbreak");
        } else if(args.length == 2){
            if(args[0].equalsIgnoreCase("messages")){
                /**
                 * addplayer / removeplayer: add or remove player to/from the list of listening messages. if the player recievs or sends a message, it will be logged. PLAYERS MODE MUST BE ACTIVATED!
                 * on: all msgs will be logged
                 * off: all msgs won't be logged anymore. Even the players which were added only.
                 * players: all msgs from the list of players will be logged
                 * console: all msgs sent from console will be logged.
                 */
                list.add("addplayer");
                list.add("removeplayer");
                list.add("on");
                list.add("off");
                list.add("players");
                list.add("console");
            } else if(args[0].equalsIgnoreCase("travel")){
                /**
                 * addplayer / removeplayer: add or remove player to/from the list of listening travel. If the player travels it will be logged
                 * on: all travels will be logged
                 * off: all travels won't be logged anymore. Even the players which were added only.
                 * world: if someone travels to a specific world, it will be logged
                 */
                list.add("addplayer");
                list.add("removeplayer");
                list.add("on");
                list.add("off");
                list.add("world");
            } else if(args[0].equalsIgnoreCase("blockplace") || args[0].equalsIgnoreCase("blockbreak")){
                /**
                 * addplayer / removeplayer: add or remove player to/from the list of listening block place. If the player places a block it will be logged
                 * on: all block places will be logged
                 * off: all block places won't be logged anymore. Even the players which were added only.
                 * block: if someone places a specific block, it will be logged
                 */
                list.add("addplayer");
                list.add("removeplayer");
                list.add("on");
                list.add("off");
                list.add("addblock");
                list.add("removeblock");
            }
        }

        return list;
    }
}
