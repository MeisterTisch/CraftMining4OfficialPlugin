package org.craftmining.craftmining4officialplugin.admin.listening;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
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
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.messages.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("on")) {
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
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.messages.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("off")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.isListening", false);
                                        PlayerManagerFile.saveConfig();
                                        player.sendMessage(ChatColor.BLUE + "Du hörst nun " + ChatColor.GREEN + "keinen" + ChatColor.BLUE + " Privatmessages mehr zu!");
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon keinen" + ChatColor.BLUE + " Privatmessages mehr zu!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages off");
                            } else if (args[1].equalsIgnoreCase("players")) {
                                if (args.length == 2) {
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.messages.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("players")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.isListening", true);
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.typeOfListening", "players");
                                        PlayerManagerFile.saveConfig();

                                        StringBuilder string = new StringBuilder();
                                        string.append(ChatColor.BLUE + "Du hörst nun Privatmessages von folgenden Leuten zu:\n");
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");

                                        if (list.isEmpty()) {
                                            string.append(ChatColor.RED + "Es ist keiner in deiner Liste drinnen!");
                                        } else {
                                            for (int i = 1; i <= list.size(); i++) {
                                                if (i == list.size())
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                                else
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                            }
                                        }

                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon deiner Liste" + ChatColor.BLUE + " zu!");
                                } else if (args.length == 3) {
                                    if (args[2].equalsIgnoreCase("list")) {
                                        StringBuilder string = new StringBuilder();
                                        string.append(ChatColor.BLUE + "Folgende Leute sind in deiner Liste:\n");
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");
                                        if (list.isEmpty()) {
                                            string.append(ChatColor.RED + "Es ist keiner in deiner Liste drinnen!");
                                        } else {
                                            for (int i = 1; i <= list.size(); i++) {
                                                if (i == list.size())
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                                else
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                            }
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages players [list]");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages players [list]");
                            } else if (args[1].equalsIgnoreCase("console")) {
                                if (args.length == 2) {
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.messages.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("console")) {
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
                                    if (!args[2].equalsIgnoreCase(player.getDisplayName())) {
                                        if (!PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList").contains(args[2])) {
                                            List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");
                                            list.add(args[2]);
                                            PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.playersList", list);
                                            PlayerManagerFile.saveConfig();
                                            player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " zu deiner Liste hinzugefügt!");
                                        } else
                                            player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist schon in deiner Liste!");
                                    } else
                                        player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht zur Liste hinzufügen!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages addplayer <Spieler>");
                            } else if (args[1].equalsIgnoreCase("removeplayer")) {
                                if (args.length == 3) {
                                    if (!args[2].equalsIgnoreCase(player.getDisplayName())) {
                                        if (PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList").contains(args[2])) {
                                            List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList");
                                            list.remove(args[2]);
                                            PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.messages.playersList", list);
                                            PlayerManagerFile.saveConfig();
                                            player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " aus deiner Liste entfernt!");

                                        } else
                                            player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist nicht in deiner Liste!");
                                    } else
                                        player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht aus der Liste entfernen, da du dich auch nicht selbst hinzufügen kannst!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages removeplayer <Spieler>");
                            } else
                                player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages <on/off/players/console/addplayer/removeplayer> ...");
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen messages <on/off/players/console/addplayer/removeplayer> ...");
                    } else if(args[0].equalsIgnoreCase("travel")){
                        if(args.length != 1){
                            if(args[1].equalsIgnoreCase("on")){
                                if(!PlayerManagerFile.getConfig().contains(player.getDisplayName()+".listener.travel.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName()+".listener.travel.typeOfListening").equalsIgnoreCase("on")){
                                    PlayerManagerFile.getConfig().set(player.getDisplayName()+".listener.travel.isListening", true);
                                    PlayerManagerFile.getConfig().set(player.getDisplayName()+".listener.travel.typeOfListening", "on");
                                    PlayerManagerFile.saveConfig();
                                    player.sendMessage(ChatColor.BLUE + "Du hörst nun" + ChatColor.GREEN + " allen " + ChatColor.BLUE + "Travels zu.");
                                } else
                                    player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon allen" + ChatColor.BLUE + " Travels zu.");
                            } else if(args[1].equalsIgnoreCase("off")){
                                if(!PlayerManagerFile.getConfig().contains(player.getDisplayName()+".listener.travel.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName()+".listener.travel.typeOfListening").equalsIgnoreCase("off")){
                                    PlayerManagerFile.getConfig().set(player.getDisplayName()+".listener.travel.isListening", false);
                                    PlayerManagerFile.getConfig().set(player.getDisplayName()+".listener.travel.typeOfListening", "off");
                                    PlayerManagerFile.saveConfig();
                                    player.sendMessage(ChatColor.BLUE + "Du hörst nun" + ChatColor.GREEN + " keinen " + ChatColor.BLUE + "Travels mehr zu.");
                                } else
                                    player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon keinen" + ChatColor.BLUE + " Travels mehr zu.");
                            } else if(args[1].equalsIgnoreCase("worlds")){
                                if(args.length == 2){
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.travel.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.travel.typeOfListening").equalsIgnoreCase("worlds")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.isListening", true);
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.typeOfListening", "worlds");
                                        PlayerManagerFile.saveConfig();

                                        StringBuilder string = new StringBuilder();
                                        string.append(ChatColor.BLUE + "Du hörst nun Travels von folgenden Welten zu:\n");
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.worldsList");
                                        if (list.isEmpty()) {
                                            string.append(ChatColor.RED + "Es ist keine Welt in deiner Liste drinnen!");
                                        } else {
                                            for (int i = 1; i <= list.size(); i++) {
                                                if (i == list.size())
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                                else
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                            }
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon deiner Liste" + ChatColor.BLUE + " zu.");
                                } else if(args.length == 3){
                                    if(args[2].equalsIgnoreCase("list")){
                                        StringBuilder string = new StringBuilder();
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.worldsList");
                                        if (list.isEmpty()) {
                                            string.append(ChatColor.RED + "Es ist keine Welt in deiner Liste drinnen!");
                                        } else {
                                            string.append(ChatColor.BLUE + "Folgende Welten sind in deiner Liste:\n");
                                            for (int i = 1; i <= list.size(); i++) {
                                                if (i == list.size())
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                                else
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                            }
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.RED+"Bitte benutze den Command so:\n"+ChatColor.GOLD + "/listen travel worlds [list]");
                                } else
                                    player.sendMessage(ChatColor.RED+"Bitte benutze den Command so:\n"+ChatColor.GOLD + "/listen travel worlds [list]");
                            } else if(args[1].equalsIgnoreCase("players")){
                                if(args.length == 2){
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.travel.typeOfListening") || !PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.travel.typeOfListening").equalsIgnoreCase("players")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.isListening", true);
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.typeOfListening", "players");
                                        PlayerManagerFile.saveConfig();

                                        StringBuilder string = new StringBuilder();
                                        string.append(ChatColor.BLUE + "Du hörst nun Travels von folgenden Spielern zu:\n");
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList");
                                        if (list.isEmpty()) {
                                            string.append(ChatColor.RED + "Es ist keiner in deiner Liste drinnen!");
                                        } else {
                                            for (int i = 1; i <= list.size(); i++) {
                                                if (i == list.size())
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                                else
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                            }
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hörst " + ChatColor.GREEN + "schon deiner Liste" + ChatColor.BLUE + " zu.");
                                } else if(args.length == 3){
                                    if(args[2].equalsIgnoreCase("list")){
                                        StringBuilder string = new StringBuilder();
                                        List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList");
                                        if (list.isEmpty()) {
                                            string.append(ChatColor.RED + "Es ist keiner in deiner Liste drinnen!");
                                        } else {
                                            string.append(ChatColor.BLUE + "Folgende Spieler sind in deiner Liste:\n");
                                            for (int i = 1; i <= list.size(); i++) {
                                                if (i == list.size())
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                                else
                                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                            }
                                        }
                                        player.sendMessage(String.valueOf(string));
                                    } else
                                        player.sendMessage(ChatColor.RED+"Bitte benutze den Command so:\n"+ChatColor.GOLD + "/listen travel players [list]");
                                } else
                                    player.sendMessage(ChatColor.RED+"Bitte benutze den Command so:\n"+ChatColor.GOLD + "/listen travel players [list]");
                            } else if(args[1].equalsIgnoreCase("addplayer")){
                                if (args.length == 3) {
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.travel.playersList")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.playersList", new ArrayList<String>());
                                        PlayerManagerFile.saveConfig();
                                        PlayerManagerFile.reload();
                                    }
                                    if (!args[2].equalsIgnoreCase(player.getDisplayName())) {
                                        if (!PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList").contains(args[2])) {
                                            List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList");
                                            list.add(args[2]);
                                            PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.playersList", list);
                                            PlayerManagerFile.saveConfig();
                                            player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " zu deiner Liste hinzugefügt!");
                                        } else
                                            player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist schon in deiner Liste!");
                                    } else
                                        player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht zur Liste hinzufügen!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen travel addplayer <Spieler>");
                            } else if (args[1].equalsIgnoreCase("removeplayer")) {
                                if (args.length == 3) {
                                    if (!args[2].equalsIgnoreCase(player.getDisplayName())) {
                                        if (PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList").contains(args[2])) {
                                            List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList");
                                            list.remove(args[2]);
                                            PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.playersList", list);
                                            PlayerManagerFile.saveConfig();
                                            player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " aus deiner Liste entfernt!");

                                        } else
                                            player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist nicht in deiner Liste!");
                                    } else
                                        player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht aus der Liste entfernen, da du dich auch nicht selbst hinzufügen kannst!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen travel removeplayer <Spieler>");
                            } else if(args[1].equalsIgnoreCase("addworld")){
                                if (args.length == 3) {
                                    if (!PlayerManagerFile.getConfig().contains(player.getDisplayName() + ".listener.travel.worldsList")) {
                                        PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.worldsList", new ArrayList<String>());
                                        PlayerManagerFile.saveConfig();
                                        PlayerManagerFile.reload();
                                    }
                                        if (!PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.worldsList").contains(args[2])) {
                                            if(Bukkit.getWorlds().contains(Bukkit.getWorld(args[2]))){
                                                List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.worldsList");
                                                list.add(args[2]);
                                                PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.worldsList", list);
                                                PlayerManagerFile.saveConfig();
                                                player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " zu deiner Liste hinzugefügt!");
                                            } else
                                                player.sendMessage(ChatColor.RED + "Bitte gib eine gültige Welt an!");
                                        } else
                                            player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist schon in deiner Liste!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen travel worldsList <Welt>");
                            } else if (args[1].equalsIgnoreCase("removeworld")) {
                                if (args.length == 3) {
                                    if (!args[2].equalsIgnoreCase(player.getDisplayName())) {
                                        if (PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.worldsList").contains(args[2])) {
                                            List<String> list = PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.worldsList");
                                            list.remove(args[2]);
                                            PlayerManagerFile.getConfig().set(player.getDisplayName() + ".listener.travel.worldsList", list);
                                            PlayerManagerFile.saveConfig();
                                            player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[2] + ChatColor.BLUE + " aus deiner Liste entfernt!");

                                        } else
                                            player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " ist nicht in deiner Liste!");
                                    } else
                                        player.sendMessage(ChatColor.RED + "Du kannst dich selbst nicht aus der Liste entfernen, da du dich auch nicht selbst hinzufügen kannst!");
                                } else
                                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen travel removeworld <Werlt>");
                            } else
                                player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" + ChatColor.GOLD + "/listen travel <on/off/players/console/addplayer/removeplayer/addworld/removeworld> ...");
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:n" +
                                 ChatColor.GOLD + "/listen travel <on/off/worlds/players/addworld/removeworld/addplayer/removeplayer> ...");

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
//            list.add("blockplace");
//            list.add("blockbreak");
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
                list.add("addworld");
                list.add("removeworld");
                list.add("on");
                list.add("off");
                list.add("players");
                list.add("worlds");
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
                list.add("players");
                list.add("blocks");
                list.add("addblock");
                list.add("removeblock");
            }
        } else if (args.length == 3) {

            if(args[0].equalsIgnoreCase("messages")){
                if(args[1].equalsIgnoreCase("addplayer"))
                    return null;
                else if(args[1].equalsIgnoreCase("removeplayer"))
                    if(sender instanceof Player player)
                        return PlayerManagerFile.getConfig().getStringList(player.getDisplayName()+".listener.messages.playersList");
                else if(args[1].equalsIgnoreCase("players"))
                    list.add("list");

            } else if(args[0].equalsIgnoreCase("travel")){
                if(args[1].equalsIgnoreCase("addplayer")){
                    Bukkit.broadcastMessage("return null");
                    return null;
                } else if(args[1].equalsIgnoreCase("removeplayer")){
                    Bukkit.broadcastMessage("return playersList");
                    if(sender instanceof Player player){
                        Bukkit.broadcastMessage("List: " + PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.travel.playersList"));
                        return PlayerManagerFile.getConfig().getStringList(player.getDisplayName()+".listener.travel.playersList");
                    }
                } else if(args[1].equalsIgnoreCase("addworld")) {
                    Bukkit.broadcastMessage("return world");
                    for(World world : Bukkit.getWorlds()){
                        Bukkit.broadcastMessage("world name: " + world.getName());
                        list.add(world.getName());
                    }
                    Bukkit.broadcastMessage("list: " + list);
                } else if(args[1].equalsIgnoreCase("removeworld")){
                    Bukkit.broadcastMessage("return worldsList");
                    if(sender instanceof Player player){
                        Bukkit.broadcastMessage("List: " + PlayerManagerFile.getConfig().getStringList(player.getDisplayName()+".listener.travel.worldsList"));
                        return PlayerManagerFile.getConfig().getStringList(player.getDisplayName()+".listener.travel.worldsList");
                    }
                } else if(args[1].equalsIgnoreCase("worlds") || args[1].equalsIgnoreCase("players")){
                    Bukkit.broadcastMessage("lists");
                    list.add("lists");
                    Bukkit.broadcastMessage("list: " + list);
                }
            }
        }
        return list;
    }
}
