package org.craftmining.craftmining4officialplugin.teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class TeamsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        //Add leader, request Join, leader gets message

        //teams <create/delete/edit/list/requestjoin/accept/decline>
        if(args.length == 1){
            list.add("create");
            list.add("delete");
            list.add("edit");
            list.add("list");
            list.add("requestjoin");
            list.add("accept");
            list.add("decline");
            list.add("invite");
        } else if (args.length == 2){
            if(args[0].equalsIgnoreCase("create")){

            }
        }


        return list;
    }
}
