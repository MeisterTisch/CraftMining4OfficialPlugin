package org.craftmining.craftmining4officialplugin.msgSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MsgCommand implements TabExecutor {
    private static Map<Player, Player> map = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("message")){
                if(args.length >= 2){
                    Player target = Bukkit.getPlayer(args[0]);
                    StringBuilder message = new StringBuilder();
                    for(String string : args){
                        if(!string.equals(args[0])) message.append(string + " ");
                    }
                    if(target != null){
                        if(player != target){
                            target.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + player.getDisplayName() +
                                    ChatColor.BLUE + " -> " + ChatColor.GREEN + "dir" + ChatColor.BLUE + "] " +
                                    ChatColor.GRAY + message);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Du" +
                                    ChatColor.BLUE + " -> " + ChatColor.GREEN + target.getDisplayName() + ChatColor.BLUE + "] " +
                                    ChatColor.GRAY + message);
                            map.put(player, target);
                            map.put(target, player);
                            Bukkit.getPluginManager().callEvent(new PrivateMessageSentEvent(player, target, message));
                        } else player.sendMessage(ChatColor.RED + "Du kannst dir selber keine Nachricht schreiben.");
                    } else
                        player.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht auf diesem Server!");
                } else
                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                            ChatColor.GOLD + "/message <Spieler> <Nachricht...>");
            } else if (label.equalsIgnoreCase("r")|| label.equalsIgnoreCase("reply")) {
                if(args.length >= 1){
                    Player target = map.get(player);
                    StringBuilder message = new StringBuilder();
                    for(String string : args){
                        message.append(string + " ");
                    }
                    if(target != null){
                        if(player != target){
                            target.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + player.getDisplayName() +
                                    ChatColor.BLUE + " -> " + ChatColor.GREEN + "dir" + ChatColor.BLUE + "] " +
                                    ChatColor.GRAY + message);
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Du" +
                                    ChatColor.BLUE + " -> " + ChatColor.GREEN + target.getDisplayName() + ChatColor.BLUE + "] " +
                                    ChatColor.GRAY + message);
                            map.put(player, target);
                            map.put(target, player);
                            Bukkit.getPluginManager().callEvent(new PrivateMessageSentEvent(player, target, message));
                        } else player.sendMessage(ChatColor.RED + "Du kannst dir selber keine Nachricht schreiben.");
                    } else
                        player.sendMessage(ChatColor.RED + "Du hast gerade niemanden zu antworten! Bitte nutze folgenden Command:\n"
                                + ChatColor.GOLD + "/msg <Spieler> <Nachricht...>");
                } else
                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                            ChatColor.GOLD + "/reply <Nachricht...>");
            }
        } else {
            if(label.equalsIgnoreCase("message") || label.equalsIgnoreCase("msg")){
                if(args.length >= 2){
                    Player target = Bukkit.getPlayer(args[0]);
                    StringBuilder message = new StringBuilder();
                    for(String string : args){
                        if(!string.equals(args[0])) message.append(string + " ");
                    }
                    if(target != null){
                            target.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Konsole" +
                                    ChatColor.BLUE + " -> " + ChatColor.GREEN + "dir" + ChatColor.BLUE + "] " +
                                    ChatColor.GRAY + message + "\n" +
                                    ChatColor.GOLD + "(Dieser Nachricht kann nicht geantwortet werden.)");
                            sender.sendMessage(ChatColor.BLUE + "[" + ChatColor.GREEN + "Du" +
                                    ChatColor.BLUE + " -> " + ChatColor.GREEN + target.getDisplayName() + ChatColor.BLUE + "] " +
                                    ChatColor.GRAY + message);
                            Bukkit.getPluginManager().callEvent(new PrivateMessageSentEvent(sender, target, message));
                    } else
                        sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht auf diesem Server!");
                } else
                    sender.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                            ChatColor.GOLD + "/message <Spieler> <Nachricht...>");
            } else if(label.equalsIgnoreCase("reply") || label.equalsIgnoreCase("r"))
                if(sender instanceof ConsoleCommandSender)
                    sender.sendMessage(ChatColor.RED + "Als Konsole kannst du keine Replies tätigen!");
                else if(sender instanceof CommandBlock)
                    sender.sendMessage(ChatColor.RED + "Als Command Block kannst du keine Replies tätigen!");
                else sender.sendMessage(ChatColor.RED + "Du kannst keine Replies tätigen!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1)
            return null;
        return new ArrayList<>();
    }
}