package org.craftmining.craftmining4officialplugin.chestProtect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ProtectCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("CraftMining4OfficialPlugin.protectChest")){
                if (args.length <= 2) {
                    Block chest = player.getTargetBlock(null, 5);
                    if (chest.getType() != Material.CHEST) {
                        boolean alreadySetUp = false;
                        for (String chestName : ProtectedChestsFile.getConfig().getStringList(player.getDisplayName() + ".chestsNameList")) {
                            if (ProtectedChestsFile.getConfig().getLocation(player.getDisplayName() + "." + chestName + ".location") == chest.getLocation()) {
                                if (args.length == 0)
                                    GUI.createAndShowGUI(player, chestName, false);
                                else
                                    player.sendMessage(ChatColor.RED + "Die Truhe ist schon eingerichtet! Bitte benutze:\n" +
                                            ChatColor.GOLD + "/protect");
                                alreadySetUp = true;
                                break;
                            }
                        }
                        if (!alreadySetUp) {
                            if (args.length == 0)
                                player.sendMessage(ChatColor.RED + "Diese Truhe ist noch nicht eingerichtet! Bitte benutze:\n"
                                        + ChatColor.GOLD + "/protect setup <chestname>");
                            else if (args.length == 2) {
                                GUI.createAndShowGUI(player, args[1], true);
                            } else
                                player.sendMessage(ChatColor.RED + "Bitte benutze den Command so:\n"
                                        + ChatColor.GOLD + "/protect setup <chestname>");
                        }

                    } else
                        player.sendMessage(ChatColor.RED + "Das ist keine Truhe!");
                }
            } else
                sender.sendMessage(ChatColor.RED + "Du hast keine Rechte für diesen Command!");
        } else
            sender.sendMessage(ChatColor.RED + "Diesen Command kann nur ein Spieler ausführen!");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        //TODO: If not set up so add "setup" to list.

//        boolean alreadySetUp = false;
//        if(sender instanceof Player player){
//            for(String chestName : ProtectedChestsFile.getConfig().getStringList(player.getDisplayName()+".chestsNameList")){
//                if(ProtectedChestsFile.getConfig().contains(player.getDisplayName()+"."+chestName)){
//                    alreadySetUp = true;
//                    break;
//                }
//            }
//        }
//        if(args.length == 1 && !alreadySetUp)
//            list.add("setup");

        return list;
    }
}
