package org.craftmining.craftmining4officialplugin.admin.listening;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;

import java.util.ArrayList;
import java.util.List;

public class ListenForEventsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("CraftMining4OfficialPlugin.listenForMessages")) {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("messages")) {
                        if (args.length != 1) {

                            if (args[1].equalsIgnoreCase("on")) {
                                if (args.length == 2) {
                                    if(!PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("on")){
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.isListening", true);
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.typeOfListening", "on");
                                        PlayerManagerFile.saveConfig();
                                        player.sendMessage(ChatColor.BLUE + "Du hörst nun " + ChatColor.GREEN + "allen" + ChatColor.BLUE + " Privatmessages zu!");
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon allen" + ChatColor.BLUE + " Privatmessages zu!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages on");


                            } else if (args[1].equalsIgnoreCase("off")) {
                                if (args.length == 2) {
                                    if(!PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("off")){
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.isListening", false);
                                        PlayerManagerFile.saveConfig();
                                        player.sendMessage(ChatColor.BLUE + "Du hörst nun " + ChatColor.GREEN + "keinen" + ChatColor.BLUE + " Privatmessages mehr zu!");
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon keinen" + ChatColor.BLUE + " Privatmessages mehr zu!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages off");


                            } else if (args[1].equalsIgnoreCase("players")) {
                                if (args.length == 2) {
                                    if(!PlayerManagerFile.getConfig().getString(player.getDisplayName()+".listener.messages.typeOfListening").equalsIgnoreCase("players")){
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.isListening", true);
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.typeOfListening", "players");
                                        PlayerManagerFile.saveConfig();

                                        StringBuilder string = new StringBuilder();
                                        string.append(ChatColor.BLUE + "Du hörst nun Privatmessages von folgenden Leuten zu:\n");
                                        for (String listPlayer : PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList")) {
                                            //TODO: BETTER MAKEN
                                            string.append(ChatColor.GREEN + listPlayer + ChatColor.GRAY + ", ");
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon deiner Liste" + ChatColor.BLUE + " zu!");
                                } else if(args.length == 3){
                                    if(args[2].equalsIgnoreCase("list")){
                                        StringBuilder string = new StringBuilder();
                                        string.append(ChatColor.BLUE + "Folgende Leute sind in deiner Liste:\n");
                                        for (String listPlayer : PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList")) {
                                            //TODO: BETTER MAKEN
                                            string.append(ChatColor.GREEN + listPlayer + ChatColor.GRAY + ", ");
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages players [list]");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages players [list]");


                            } else if (args[1].equalsIgnoreCase("console")) {
                                if (args.length == 2) {
                                    if(!PlayerManagerFile.getConfig().getString(player.getDisplayName()+".listener.messages.typeOfListening").equalsIgnoreCase("console")){
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.isListening", true);
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.typeOfListening", "console");
                                        PlayerManagerFile.saveConfig();
                                        player.sendMessage(ChatColor.BLUE + "Du hörst nun Privatmessages von der " + ChatColor.GREEN + "Konsole" + ChatColor.BLUE + " zu!");
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon die Privatmessages der Konsole" + ChatColor.BLUE + " zu!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages console");


                            } else if (args[1].equalsIgnoreCase("addplayer")) {
                                if (args.length == 3) {
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.messages.playersList")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.playersList", new ArrayList<String>());
                                        PlayerManagerFile.saveConfig();
                                        PlayerManagerFile.reload();
                                    }
                                    if (!PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList").contains(args[2])) {
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");
                                        Bukkit.broadcastMessage(String.valueOf(list));
                                        list.add(args[2]);
                                        Bukkit.broadcastMessage(String.valueOf(list));
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.playersList", list);
                                        PlayerManagerFile.saveConfig();
                                        player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " zu deiner Liste hinzugefügt!");

                                    } else
                                        player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist schon in deiner Liste!");

                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages addplayer <Spieler>");


                            } else if (args[1].equalsIgnoreCase("removeplayer")) {
                                if (args.length == 3) {
                                    if (PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList").contains(args[2])) {
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");
                                        Bukkit.broadcastMessage(String.valueOf(list));
                                        list.remove(args[2]);
                                        Bukkit.broadcastMessage(String.valueOf(list));
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.playersList", list);
                                        PlayerManagerFile.saveConfig();
                                        player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " aus deiner Liste entfernt!");

                                    } else
                                        player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist nicht in deiner Liste!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages removeplayer <Spieler>");
                            }
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages <on/off/players/console/addplayer/removeplayer> ...");

                    } else
                        player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen <messages/travel/blockplace/blockbreak> ...");

                } else
                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen <messages/travel/blockplace/blockbreak> ...");
            } else player.sendMessage(ChatColor.RED + "Du hast dazu keine Rechte!");
        } else sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 1) {
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
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("messages")) {
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
            } else if (args[0].equalsIgnoreCase("travel")) {
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
            } else if (args[0].equalsIgnoreCase("blockplace") || args[0].equalsIgnoreCase("blockbreak")) {
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
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("messages") && args[1].equalsIgnoreCase("addplayer")) {
                return null;
            } else if (args[0].equalsIgnoreCase("messages") && args[1].equalsIgnoreCase("removeplayer")) {
                if (sender instanceof Player player) {
                    return PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");
                }
            } else if(args[0].equalsIgnoreCase("messages") && args[1].equalsIgnoreCase("players"))
                list.add("list");
        }

        return list;
    }
}
