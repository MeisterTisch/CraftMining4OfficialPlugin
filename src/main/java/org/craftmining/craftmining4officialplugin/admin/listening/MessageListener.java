package org.craftmining.craftmining4officialplugin.admin.listening;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.craftmining.craftmining4officialplugin.fileManagers.PlayerManagerFile;
import org.craftmining.craftmining4officialplugin.msgSystem.PrivateMessageSentEvent;

import java.util.ArrayList;
import java.util.List;

public class MessageListener implements Listener {
    @EventHandler
    public void messageSentEvent(PrivateMessageSentEvent event) {
        PlayerManagerFile.reload();
        List<Player> listeningPlayersList = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerManagerFile.getConfig().getBoolean(player.getDisplayName() + ".listener.messages.isListening")) {
                listeningPlayersList.add(player);
            }
        }

        for (Player player : listeningPlayersList) {
            // ON
            if (PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("on")
                    && event.getSender() != player && event.getTarget() != player) {
                if (event.getSender() instanceof Player sender)
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + sender.getDisplayName() + ChatColor.BLUE + " -> " + ChatColor.GREEN + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                else if (event.getSender() instanceof ConsoleCommandSender)
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Konsole" + ChatColor.BLUE + " -> " + ChatColor.GREEN + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                else if (event.getSender() instanceof CommandBlock)
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Command Block" + ChatColor.BLUE + " -> " + ChatColor.GREEN + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                else
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Unbekannte Quelle" + ChatColor.BLUE + " -> " + ChatColor.GREEN + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());

                //CONSOLE
            } else if (PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("console")
                    && event.getSender() != player && event.getTarget() != player) {
                if (event.getSender() instanceof ConsoleCommandSender)
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "" + ChatColor.UNDERLINE + "Konsole" + ChatColor.BLUE + " -> " + ChatColor.GREEN + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());

                //PLAYERS
            } else if (PlayerManagerFile.getConfig().getString(player.getDisplayName() + ".listener.messages.typeOfListening").equalsIgnoreCase("players")
                    && event.getSender() != player && event.getTarget() != player) {
                for (String listPlayer : PlayerManagerFile.getConfig().getStringList(player.getDisplayName() + ".listener.messages.playersList")) {
                    if (Bukkit.getPlayer(listPlayer) == event.getTarget()) {
                        if (event.getSender() instanceof Player sender) {
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + sender.getDisplayName() + ChatColor.BLUE + " -> " + ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                        } else if (event.getSender() instanceof ConsoleCommandSender) {
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Konsole" + ChatColor.BLUE + " -> " + ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                        } else if (event.getSender() instanceof CommandBlock) {
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Command Block" + ChatColor.BLUE + " -> " + ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                        } else {
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Unbekannte Quelle" + ChatColor.BLUE + " -> " + ChatColor.GREEN + "" + ChatColor.UNDERLINE + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                        }
                    } else if (Bukkit.getPlayer(listPlayer) == event.getSender()) {
                        Player sender = (Player) event.getSender();
                        player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "" + ChatColor.UNDERLINE + sender.getDisplayName() + ChatColor.BLUE + " -> " + ChatColor.GREEN + event.getTarget().getDisplayName() + ChatColor.BLUE + "] " + ChatColor.GRAY + event.getMessage());
                    }
                }
            }
        }
    }
}

