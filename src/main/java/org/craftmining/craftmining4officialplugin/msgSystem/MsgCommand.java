package org.craftmining.craftmining4officialplugin.msgSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MsgCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){

            if(label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("message")){
                if(args.length >= 2){
                    Player target = Bukkit.getPlayer(args[0]);
                    StringBuilder message = new StringBuilder();

                    for(String string : args){
                        if(!string.equals(args[0])) message.append(string);
                    }

                    if(target != null){
                        target.sendMessage(ChatColor.BLUE + "[ " + ChatColor.GREEN + player.getDisplayName() +
                                ChatColor.BLUE + " -> " + ChatColor.GREEN + "dir" + ChatColor.BLUE + " ] " +
                                ChatColor.GRAY + message);
                    } else
                        player.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " ist nicht auf diesem Server!");
                } else
                    player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n" +
                            ChatColor.GOLD + "/msg <Spieler> <Nachricht...>");
            } else if (label.equalsIgnoreCase("r")|| label.equalsIgnoreCase("reply")) {

            }


        } else {



        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1)
            for (Player player : Bukkit.getOnlinePlayers())
                list.add(player.getDisplayName());


        return list;
    }
}
