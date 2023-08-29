package org.craftmining.craftmining4officialplugin.tpSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPCommand implements TabExecutor {
    TeleportQueue tpQueue = TeleportQueue.INSTANCE;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 0) {
                switch (args[0]) {
                    case "to", "here" -> {
                        if (args.length == 2) {
                            Player receiver = Bukkit.getPlayer(args[1]);
                            if (receiver == null) {
                                player.sendMessage(ChatColor.GOLD + args[1] + ChatColor.RED + " ist nicht auf dem Server!");
                                return true;
                            }
                            if(player == receiver){
                                player.sendMessage(ChatColor.RED + "Du kannst dir selber keine Anfrage schicken!");
                                return true;
                            }
                            
                            if (!TPFile.getConfig().contains(receiver.getDisplayName()) || TPFile.getConfig().getBoolean(receiver.getDisplayName())) {
                                if (args[0].equals("to")) {
                                    tpQueue.TELEPORT_REQUESTS.put(receiver, new TeleportRequest(player, false));
                                    player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + receiver.getDisplayName() + ChatColor.BLUE + " angefragt, dich zu ihm zu teleportieren.");
                                    receiver.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.BLUE + " hat angefragt sich zu dir zu teleportieren!\n" +
                                            ChatColor.BLUE + "Nutze " + ChatColor.GREEN + "/tp accept/decline " + ChatColor.BLUE + " um anzunehmen/abzulehnen.");
                                } else if (args[0].equals("here")) {
                                    tpQueue.TELEPORT_REQUESTS.put(receiver, new TeleportRequest(player, true));
                                    player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + receiver.getDisplayName() + ChatColor.BLUE + " angefragt, ihn zu dir zu teleportieren.");
                                    receiver.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.BLUE + " hat angefragt dich zu ihm zu teleportieren!\n" +
                                            ChatColor.BLUE + "Nutze " + ChatColor.GREEN + "/tp accept/decline " + ChatColor.BLUE + " um anzunehmen/abzulehnen.");
                                }
                                return true;
                            } else
                                player.sendMessage(ChatColor.GOLD + args[1] + ChatColor.RED + " hat TP-Requests aus.");
                        } else if (args.length == 1)
                            player.sendMessage(ChatColor.RED + "Bitte gib einen Spieler an!");
                        else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/tp to <player>");

                    }
                    case "accept" -> {
                        if (args.length == 1) {
                            TeleportRequest tpRequest = tpQueue.TELEPORT_REQUESTS.get(player);
                            if (tpRequest == null) {
                                player.sendMessage(ChatColor.RED + "Du hast zurzeit keinen Request.");
                                return true;
                            }
                            if (!tpRequest.isReversed) {
                                tpQueue.PREVIOUS_LOCATIONS.put(tpRequest.requestee, tpRequest.requestee.getLocation());
                                tpRequest.requestee.teleport(player);
                                tpQueue.TELEPORT_REQUESTS.remove(player);
                                player.sendMessage(ChatColor.BLUE + "Du hast dich zu " + ChatColor.GREEN + tpRequest.requestee.getDisplayName() + ChatColor.BLUE + " teleportiert.");
                                tpRequest.requestee.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.BLUE + " hat sich zu dir teleportiert.");
                                return true;
                            } else if (tpRequest.isReversed) {
                                tpQueue.PREVIOUS_LOCATIONS.put(player, player.getLocation());
                                player.teleport(tpRequest.requestee);
                                tpQueue.TELEPORT_REQUESTS.remove(player);
                                tpRequest.requestee.sendMessage(ChatColor.BLUE + "Du hast dich zu " + ChatColor.GREEN + player.getDisplayName() + ChatColor.BLUE + " teleportiert.");
                                player.sendMessage(ChatColor.GREEN + tpRequest.requestee.getDisplayName() + ChatColor.BLUE + " hat sich zu dir teleportiert.");
                                return true;
                            }
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/tp accept");


                    }
                    case "decline" -> {
                        if (args.length == 1) {
                            TeleportRequest tpRequest = tpQueue.TELEPORT_REQUESTS.get(player);
                            if (tpRequest == null) {
                                player.sendMessage(ChatColor.RED + "Du hast zurzeit keinen Request.");
                                return true;
                            }
                            tpRequest.requestee.sendMessage(ChatColor.GOLD + player.getName() + ChatColor.RED + " hat deine TP-Request abgelehnt.");
                            player.sendMessage(ChatColor.RED + "Du hast die TP-Request von " + ChatColor.GOLD + tpRequest.requestee.getDisplayName() + ChatColor.RED + " abgelehnt.");
                            tpQueue.TELEPORT_REQUESTS.remove(player);
                            return true;
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/tp decline");
                    }
                    case "toggle" -> {
                        if (args.length == 1) {
                            if (TPFile.getConfig().contains(player.getDisplayName()) || TPFile.getConfig().getBoolean(player.getDisplayName())) {
                                TPFile.getConfig().set(player.getDisplayName(), false);
                                player.sendMessage(ChatColor.BLUE + "Du hast nun TP-Requests " + ChatColor.RED + "aus" + ChatColor.BLUE + ".");
                                TPFile.saveConfig();
                            } else if (!TPFile.getConfig().getBoolean(player.getDisplayName())) {
                                TPFile.getConfig().set(player.getDisplayName(), true);
                                player.sendMessage(ChatColor.BLUE + "Du hast nun TP-Requests " + ChatColor.GREEN + "an" + ChatColor.BLUE + ".");
                                TPFile.saveConfig();
                            }
                        } else if (args.length == 2) {
                            switch (args[1]) {
                                case "on" -> {
                                    if (!TPFile.getConfig().getBoolean(player.getDisplayName())) {
                                        TPFile.getConfig().set(player.getDisplayName(), true);
                                        player.sendMessage(ChatColor.BLUE + "Du hast nun TP-Requests " + ChatColor.GREEN + "an" + ChatColor.BLUE + ".");
                                        TPFile.saveConfig();
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hast schon TP-Requests " + ChatColor.GREEN + "an" + ChatColor.BLUE + ".");
                                }
                                case "off" -> {
                                    if (TPFile.getConfig().getBoolean(player.getDisplayName())) {
                                        TPFile.getConfig().set(player.getDisplayName(), false);
                                        player.sendMessage(ChatColor.BLUE + "Du hast nun TP-Requests " + ChatColor.RED + "aus" + ChatColor.BLUE + ".");
                                        TPFile.saveConfig();
                                    } else
                                        player.sendMessage(ChatColor.BLUE + "Du hast schon TP-Requests " + ChatColor.RED + "aus" + ChatColor.BLUE + ".");
                                }
                                default -> {
                                    player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                            + ChatColor.GOLD + "/tp toggle [on/off]");
                                }
                            }
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                    + ChatColor.GOLD + "/tp toggle [on/off]");
                    }
                    case "back" -> {
                        if(args.length == 1){
                            Location previousLocation = tpQueue.PREVIOUS_LOCATIONS.get(player);
                            if (previousLocation == null) {player.sendMessage(ChatColor.RED + "Es wurde keine Location gefunden."); return true;}
                            player.teleport(previousLocation);
                            player.sendMessage(ChatColor.BLUE + "Du wurdest zurück teleportiert.");
                            tpQueue.PREVIOUS_LOCATIONS.remove(player);
                            return true;
                        } else
                            player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                                + ChatColor.GOLD + "/tp back");
                    }
                }


            } else
                player.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n"
                        + ChatColor.GOLD + "/tp <to/here/accept/decline/toggle/back> ...");

        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("to");
            list.add("here");
            list.add("toggle");
            list.add("accept");
            list.add("decline");
            list.add("back");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "to", "here" -> {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!TPFile.getConfig().contains(player.getDisplayName()) || TPFile.getConfig().getBoolean(player.getDisplayName())) {
                            list.add(player.getDisplayName());
                        }
                    }
                }
                case "toggle" -> {
                    list.add("on");
                    list.add("off");
                }
            }
        }

        return list;
    }
}
