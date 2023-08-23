package org.craftmining.craftmining4officialplugin.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.craftmining.craftmining4officialplugin.newPlayers.JoinAndQuitListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TeamsCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("CraftMining4OfficialPlugin.teams")){
            if(args.length != 0){
                if(args[0].equalsIgnoreCase("create")){
                    //teams create <name> <displayname> <color>
                    if(args.length == 4){
                        if(getColorsList().contains(args[3])){
                            if(!TeamsFile.getConfig().getStringList("takenColors").contains(args[3])){
                                if(!TeamsFile.getConfig().getStringList("teamsList").contains(args[1].toLowerCase(Locale.ROOT))){
                                    TeamsFile.getConfig().set(args[1].toLowerCase(Locale.ROOT) + ".displayname", args[2]);
                                    TeamsFile.getConfig().set(args[1].toLowerCase(Locale.ROOT) + ".color", args[3]);
                                    List<String> list = TeamsFile.getConfig().getStringList("teamsList");
                                    list.add(args[1].toLowerCase(Locale.ROOT));
                                    TeamsFile.getConfig().set("teamsList", list);
                                    List<String> takenColors = TeamsFile.getConfig().getStringList("takenColors");
                                    takenColors.add(args[3]);
                                    TeamsFile.getConfig().set("takenColors", takenColors);
                                    TeamsFile.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "Team wurde erstellt!\n" +
                                            ChatColor.BLUE + "Name: " + ChatColor.GREEN + args[1].toLowerCase(Locale.ROOT) + "\n" +
                                            ChatColor.BLUE + "Displayname: " + ChatColor.GREEN + args[2] + "\n" +
                                            ChatColor.BLUE + "Color: " + ChatColor.valueOf(args[3]) + args[3]);
                                } else
                                    sender.sendMessage(ChatColor.GOLD + args[1].toLowerCase(Locale.ROOT) + ChatColor.RED+" gibt es schon!");
                            } else {
                                for(String team : TeamsFile.getConfig().getStringList("teamsList")){
                                    if(TeamsFile.getConfig().getString(team+".color").equals(args[3])){
                                        sender.sendMessage(ChatColor.RED + "Die Farbe " +
                                                ChatColor.valueOf(args[3]) + args[3] + ChatColor.RED +
                                                " wird schon von Team " + ChatColor.GOLD + team + ChatColor.RED + " genutzt.");
                                        break;
                                    }
                                }
                            }
                        } else
                            sender.sendMessage(ChatColor.RED + "Bitte gib eine korrekte Farbe an.");
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams create <name> <displayname> <color>");
                } else if(args[0].equalsIgnoreCase("delete")){
                    //teams delete <name> confirm
                    if(TeamsFile.getConfig().contains(args[1].toLowerCase(Locale.ROOT))){
                        if(args.length == 2){
                            sender.sendMessage(ChatColor.RED + "Willst du wirklich Team " + ChatColor.GOLD + args[1] + ChatColor.RED + " löschen?\n" +
                                    ChatColor.RED + "Dann schreibe: " + ChatColor.GOLD + "/teams delete " + args[1] + " confirm");
                        } else if (args.length == 3 && args[2].equals("confirm")) {
                            for (String player : TeamsFile.getConfig().getStringList(args[1] + ".playersList")) {
                                if (Bukkit.getPlayer(player) != null) {
                                    Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "Dein Team, in dem du drinnen warst, wurde gelöscht!");
                                    JoinAndQuitListener.prepareAllPlayers();
                                }
                            }
                            List<String> list = TeamsFile.getConfig().getStringList("teamsList");
                            list.remove(args[1].toLowerCase(Locale.ROOT));
                            TeamsFile.getConfig().set("teamsList", list);
                            List<String> takenColors = TeamsFile.getConfig().getStringList("takenColors");
                            takenColors.remove(TeamsFile.getConfig().getString(args[1].toLowerCase(Locale.ROOT)+".color"));
                            TeamsFile.getConfig().set("takenColors", takenColors);
                            TeamsFile.getConfig().set(args[1].toLowerCase(Locale.ROOT), "");
                            TeamsFile.saveConfig();
                            sender.sendMessage(ChatColor.BLUE + "Team " + ChatColor.GREEN + args[1] + ChatColor.BLUE + " wurde gelöscht!");
                        } else
                            sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                    ChatColor.GOLD + "/teams delete <name> confirm");
                    } else
                        sender.sendMessage(ChatColor.RED + "Das Team " + ChatColor.GOLD + args[1] + ChatColor.RED + " gibt es nicht!");
                } else if(args[0].equalsIgnoreCase("edit")){
                    //teams edit <name> <color/displayname> <value>
                    if (args.length == 4) {
                        if (TeamsFile.getConfig().getStringList("teamsList").contains(args[1].toLowerCase(Locale.ROOT))) {
                            if (args[2].equalsIgnoreCase("color")) {
                                if (getColorsList().contains(args[3])) {
                                    if (!TeamsFile.getConfig().getString(args[1].toLowerCase(Locale.ROOT) + ".color").equals(args[3])) {
                                        TeamsFile.getConfig().set(args[1].toLowerCase(Locale.ROOT) + ".color", args[3]);
                                        TeamsFile.saveConfig();
                                        sender.sendMessage(ChatColor.BLUE + "Farbe " + ChatColor.valueOf(args[3]) + args[3] +
                                                ChatColor.BLUE + " wurde gesetzt.");
                                        for (String player : TeamsFile.getConfig().getStringList(args[1].toLowerCase(Locale.ROOT) + ".playersList")){
                                            if(Bukkit.getPlayer(player) != null)
                                                Bukkit.getPlayer(player).sendMessage(ChatColor.BLUE + "Die " + ChatColor.GREEN + "Farbe " + ChatColor.BLUE + " deines Teams wurde auf " + ChatColor.valueOf(args[3]) + args[3] + ChatColor.BLUE + " geändert.");
                                            JoinAndQuitListener.prepareAllPlayers();
                                        }
                                    } else
                                        sender.sendMessage(ChatColor.RED + "Es hat sich nichts geändert!");
                                } else
                                    sender.sendMessage(ChatColor.RED + "Bitte gib eine richtige Farbe an!");
                            } else if (args[2].equalsIgnoreCase("displayname")) {
                                if (!TeamsFile.getConfig().getString(args[1].toLowerCase(Locale.ROOT) + ".displayname").equalsIgnoreCase(args[3])) {
                                    TeamsFile.getConfig().set(args[1].toLowerCase(Locale.ROOT) + ".displayname", args[3]);
                                    TeamsFile.saveConfig();
                                    for (String player : TeamsFile.getConfig().getStringList(args[1].toLowerCase(Locale.ROOT) + ".playersList")){
                                        JoinAndQuitListener.prepareAllPlayers();
                                        if(Bukkit.getPlayer(player) != null)
                                            Bukkit.getPlayer(player).sendMessage(ChatColor.BLUE + "Der " + ChatColor.GREEN + "Displayname" + ChatColor.BLUE + " deines Teams wurde auf " + ChatColor.GREEN + args[3] + ChatColor.BLUE + " geändert.");
                                    }
                                    sender.sendMessage(ChatColor.BLUE + "Displayname " + ChatColor.GREEN + args[3] +
                                            ChatColor.BLUE + " wurde gesetzt.");
                                } else
                                    sender.sendMessage(ChatColor.RED + "Es hat sich nichts geändert!");
                            } else
                                sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                        ChatColor.GOLD + "/teams edit <teamname> <color/displayname> <value>");
                        } else
                            sender.sendMessage(ChatColor.GOLD + args[1].toLowerCase(Locale.ROOT) +
                                    ChatColor.RED + " gibt es nicht!");
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams edit <teamname> <color/displayname> <value>");
                } else if(args[0].equalsIgnoreCase("list")){
                    //teams list <team/color> [name]
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("team")) {
                            List<String> list = TeamsFile.getConfig().getStringList("teamsList");
                            StringBuilder string = new StringBuilder();
                            if(list.size() == 0)
                                string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + "keine" + ChatColor.BLUE + " Teams.");
                            else if(list.size() == 1)
                                string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + list.size() + ChatColor.BLUE +
                                        " Team: \n");
                            else
                                string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + list.size() + ChatColor.BLUE +
                                        " Teams: \n");
                            for (int i = 1; i <= list.size(); i++) {
                                if (i == list.size())
                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                else
                                    string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                            }
                            sender.sendMessage(string.toString());
                        } else if (args[1].equalsIgnoreCase("color")) {
                            List<String> list = getColorsList();
                            list.removeAll(TeamsFile.getConfig().getStringList("takenColors"));
                            StringBuilder string = new StringBuilder();
                            if(list.size() == 0)
                                string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + "keine" + ChatColor.BLUE + " freie Farbe mehr.");
                            else if(list.size() == 1)
                                string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + list.size() + ChatColor.BLUE +
                                        " freie Farbe: \n");
                            else
                                string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + list.size() + ChatColor.BLUE +
                                        " freie Farben: \n");
                            for (int i = 1; i <= list.size(); i++) {
                                if (i == list.size())
                                    string.append(ChatColor.valueOf(list.get(i - 1)) + list.get(i - 1) + ChatColor.BLUE + ".");
                                else
                                    string.append(ChatColor.valueOf(list.get(i - 1)) + list.get(i - 1) + ChatColor.BLUE + ", ");
                            }
                            sender.sendMessage(string.toString());
                        } else
                            sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                    ChatColor.GOLD + "/teams list <team/color> [team]");
                    } else if (args.length == 3) {
                        if (args[1].equalsIgnoreCase("team")) {
                            if (TeamsFile.getConfig().getStringList("teamsList").contains(args[2].toLowerCase(Locale.ROOT))) {
                                if (TeamsFile.getConfig().contains(args[2].toLowerCase(Locale.ROOT) + ".playersList")
                                        && !TeamsFile.getConfig().getStringList(args[2].toLowerCase(Locale.ROOT) + ".playersList").isEmpty()) {
                                    List<String> list = TeamsFile.getConfig().getStringList(args[2].toLowerCase(Locale.ROOT) + ".playersList");
                                    StringBuilder string = new StringBuilder();
                                    string.append(ChatColor.BLUE + "Es gibt " + ChatColor.GREEN + list.size() + ChatColor.BLUE +
                                            " Spieler in diesem Team: \n");
                                    for (int i = 1; i <= list.size(); i++) {
                                        if (i == list.size())
                                            string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ".");
                                        else
                                            string.append(ChatColor.GREEN + list.get(i - 1) + ChatColor.BLUE + ", ");
                                    }
                                    sender.sendMessage(string.toString());
                                } else
                                    sender.sendMessage(ChatColor.RED + "Team " + ChatColor.GOLD + args[2].toLowerCase(Locale.ROOT) +
                                            ChatColor.RED + " hat keine Spieler!");
                            } else
                                sender.sendMessage(ChatColor.GOLD + args[2].toLowerCase(Locale.ROOT) + ChatColor.RED + " gibt es nicht!");
                        } else if (args[1].equalsIgnoreCase("color")) {

                            sender.sendMessage(ChatColor.RED + "Zum rausfinden, welches Team die Farbe hat, nutze folgenden Command:\n" +
                                    ChatColor.GOLD + "/teams whereis color <color>");
                        } else
                            sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                    ChatColor.GOLD + "/teams list <team/color> [team]");
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams list <team/color> [team]");
                } else if(args[0].equalsIgnoreCase("addplayer")){
                    //teams addplayer <player> <team>
                    if(args.length == 3){
                        boolean isInTeam = false;
                        for(String team : TeamsFile.getConfig().getStringList("teamsList")){
                            team = team.toLowerCase(Locale.ROOT);
                            if(TeamsFile.getConfig().getStringList(team+".playersList").contains(args[1])){
                                sender.sendMessage(ChatColor.RED + "Spieler " + ChatColor.GOLD + args[1] +
                                        ChatColor.RED + " ist bereits im Team " + ChatColor.GOLD + team + ChatColor.RED + "!");
                                isInTeam = true;
                                break;
                            }
                        }
                        if(!isInTeam) {
                            if(TeamsFile.getConfig().getStringList("teamsList").contains(args[2].toLowerCase(Locale.ROOT))){
                                List<String> list = TeamsFile.getConfig().getStringList(args[2].toLowerCase(Locale.ROOT) + ".playersList");
                                list.add(args[1]);
                                TeamsFile.getConfig().set(args[2].toLowerCase(Locale.ROOT) + ".playersList", list);
                                TeamsFile.saveConfig();
                                if (sender instanceof Player player && player.getDisplayName().equals(args[1])) {
                                    sender.sendMessage(ChatColor.BLUE + "Du bist Team " + ChatColor.valueOf(TeamsFile.getConfig().getString(args[2].toLowerCase(Locale.ROOT) + ".color")) +
                                            args[2].toLowerCase(Locale.ROOT) + ChatColor.BLUE + " beigetreten.");
                                } else {
                                    sender.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.GREEN + args[1] + ChatColor.BLUE + " in Team " +
                                            ChatColor.valueOf(TeamsFile.getConfig().getString(args[2].toLowerCase(Locale.ROOT) + ".color")) + args[2].toLowerCase(Locale.ROOT) + ChatColor.BLUE + " reingetan!");
                                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1])))
                                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.BLUE + "Du wurdest in Team " +
                                            ChatColor.valueOf(TeamsFile.getConfig().getString(args[2].toLowerCase(Locale.ROOT) + ".color")) + args[2].toLowerCase(Locale.ROOT)
                                            + ChatColor.BLUE + " reingesteckt!");
                                }
                                for (String player : TeamsFile.getConfig().getStringList(args[2].toLowerCase(Locale.ROOT) + ".playersList")) {
                                    if (!args[1].equals(player) && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(player))) {
                                        Bukkit.getPlayer(player).sendMessage(ChatColor.GREEN + args[1] + ChatColor.BLUE + " spielt nun bei euch mit!");
                                    }
                                }
                                JoinAndQuitListener.prepareAllPlayers();
                            } else
                                sender.sendMessage(ChatColor.GOLD + args[2] + ChatColor.RED + " gibt es nicht!");
                        }
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams addplayer <playername> <teamname>");
                } else if(args[0].equalsIgnoreCase("removeplayer")){
                    //teams removeplayer <player> confirm
                    if(args.length == 2){
                        boolean isInTeam = false;
                        for (String team : TeamsFile.getConfig().getStringList("teamsList")) {
                            team = team.toLowerCase(Locale.ROOT);
                            if (TeamsFile.getConfig().getStringList(team + ".playersList").contains(args[1])) {
                                if (sender instanceof Player player && player.getDisplayName().equals(args[1]))
                                    sender.sendMessage(ChatColor.RED + "Willst du wirklich Team " + ChatColor.GOLD + team + ChatColor.RED + " verlassen?\n"
                                            + ChatColor.RED + "Dann schreibe: " + ChatColor.GOLD + "/teams removeplayer " + args[1] + " confirm");
                                else
                                    sender.sendMessage(ChatColor.RED + "Willst du wirklich " + ChatColor.GOLD + args[1] +
                                            ChatColor.RED + " aus Team " + ChatColor.GOLD + team + ChatColor.RED + " entfernen?\n"
                                            + ChatColor.RED + "Dann schreibe: " + ChatColor.GOLD + "/teams removeplayer " + args[1] + " confirm");
                                isInTeam = true;
                                break;
                            }

                        }
                        if (!isInTeam) {
                            if (sender instanceof Player player && player.getDisplayName().equals(args[1]))
                                sender.sendMessage(ChatColor.RED + "Du bist in keinem Team!");
                            else
                                sender.sendMessage(ChatColor.GOLD + args[1] + ChatColor.RED + " ist in keinem Team!");
                        }
                    } else if (args.length == 3 && args[2].equals("confirm")) {
                        boolean isInTeam = false;
                        for (String team : TeamsFile.getConfig().getStringList("teamsList")) {
                            team = team.toLowerCase(Locale.ROOT);
                            if (TeamsFile.getConfig().getStringList(team + ".playersList").contains(args[1])) {
                                List<String> list = TeamsFile.getConfig().getStringList(team + ".playersList");
                                list.remove(args[1]);
                                TeamsFile.getConfig().set(team + ".playersList", list);
                                TeamsFile.saveConfig();
                                JoinAndQuitListener.prepareAllPlayers();
                                if (sender instanceof Player player && player.getDisplayName().equals(args[1]))
                                    sender.sendMessage(ChatColor.RED + "Du hast Team " + ChatColor.GOLD + team + ChatColor.RED + " verlassen.");
                                else {
                                    sender.sendMessage(ChatColor.GOLD + args[1] + ChatColor.RED + " aus Team " + ChatColor.GOLD + team + ChatColor.RED + " entfernt.");
                                    if (Bukkit.getPlayer(args[1]) != null) {
                                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.RED + "Du wurdest aus Team " + ChatColor.GOLD + team + ChatColor.RED + " entfernt.");
                                    }
                                }
                                for (String player : TeamsFile.getConfig().getStringList(team + ".playersList")) {
                                    if (Bukkit.getPlayer(player) != null) {
                                        Bukkit.getPlayer(player).sendMessage(ChatColor.GOLD + args[1] + ChatColor.RED + " wurde aus deinem Team entfernt.");
                                    }
                                }
                                JoinAndQuitListener.prepareAllPlayers();
                                isInTeam = true;
                                break;
                            }
                        }
                        if (!isInTeam) {
                            if (sender instanceof Player player && player.getDisplayName().equals(args[1]))
                                sender.sendMessage(ChatColor.RED + "Du bist in keinem Team!");
                            else
                                sender.sendMessage(ChatColor.GOLD + args[1] + ChatColor.RED + " ist in keinem Team!");
                        }
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams removeplayer <player> confirm");
                } else if(args[0].equalsIgnoreCase("whereis")){
                    //teams whereis <player/color> <name>
                    if(args.length == 3){
                        if(args[1].equalsIgnoreCase("player")){
                            boolean isInTeam = false;
                            for (String team : TeamsFile.getConfig().getStringList("teamsList")) {
                                team = team.toLowerCase(Locale.ROOT);
                                if (TeamsFile.getConfig().getStringList(team + ".playersList").contains(args[2])) {
                                    sender.sendMessage(ChatColor.BLUE + "Spieler " + ChatColor.GREEN + args[2] +
                                            ChatColor.BLUE + " ist im Team " + ChatColor.GREEN + team + ChatColor.BLUE + ".");
                                    isInTeam = true;
                                    break;
                                }
                            }
                            if (!isInTeam)
                                sender.sendMessage(ChatColor.BLUE + "Spieler " + ChatColor.GREEN + args[2] +
                                        ChatColor.BLUE + " ist in keinem Team.");
                        } else if(args[1].equalsIgnoreCase("color")){
                            if(getColorsList().contains(args[2])){
                                boolean hasTeam = false;
                                for (String team : TeamsFile.getConfig().getStringList("teamsList")) {
                                    team = team.toLowerCase(Locale.ROOT);
                                    if (TeamsFile.getConfig().getString(team + ".color").equals(args[2])) {
                                        sender.sendMessage(ChatColor.BLUE + "Farbe " + ChatColor.valueOf(args[2]) + args[2] +
                                                ChatColor.BLUE + " wird von Team " + ChatColor.GREEN + team + ChatColor.BLUE + " genutzt.");
                                        hasTeam = true;
                                        break;
                                    }
                                }
                                if (!hasTeam)
                                    sender.sendMessage(ChatColor.BLUE + "Farbe " + ChatColor.valueOf(args[2]) + args[2] +
                                            ChatColor.BLUE + " wird von keinem Team genutzt.");
                            } else
                                sender.sendMessage(ChatColor.RED + "Bitte gib eine richtige Farbe ein!");
                        } else
                            sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                    ChatColor.GOLD + "/teams whereis <player/color> <name>");
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams whereis <player/color> <name>");
                } else if (args[0].equalsIgnoreCase("reload")) {
                    //teams reload
                    if (args.length == 1) {
                        JoinAndQuitListener.prepareAllPlayers();
                        sender.sendMessage(ChatColor.BLUE + "Alle Teams wurden neugeladen!");
                    } else
                        sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                                ChatColor.GOLD + "/teams reload");
                } else
                    sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                            ChatColor.GOLD + "/teams <create/delete/edit/list/addplayer/removeplayer> ...");
            } else
                sender.sendMessage(ChatColor.RED + "Bitte nutze den Command so:\n" +
                        ChatColor.GOLD + "/teams <create/delete/edit/list/addplayer/removeplayer> ...");
        } else
            sender.sendMessage(ChatColor.RED + "Für diesen Command hast du nicht die Rechte!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        //teams <create/delete/edit/list/addplayer/removeplayer>
        if (args.length == 1) {
            list.add("create");
            list.add("delete");
            list.add("edit");
            list.add("list");
            list.add("addplayer");
            list.add("removeplayer");
            list.add("whereis");
            list.add("reload");
        } else if (args.length == 2) {
            //teams create <name> <displayname> <color> (Weitere Tabargumente nicht nötig)
            //teams list <team/color> [team]
            //teams delete <name> [confirm]
            //teams edit <name> ...
            if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("edit")) {
                return TeamsFile.getConfig().getStringList("teamsList");
            } else if (args[0].equalsIgnoreCase("addplayer") || args[0].equalsIgnoreCase("removeplayer")) {
                return null;
            } else if (args[0].equalsIgnoreCase("whereis")) {
                list.add("player");
                list.add("color");
            } else if(args[0].equalsIgnoreCase("list")){
                list.add("team");
                list.add("color");
            }
        } else if(args.length == 3){
            if(args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("removeplayer")){
                list.add("confirm");
            } else if(args[0].equalsIgnoreCase("addplayer")){
                return TeamsFile.getConfig().getStringList("teamsList");
            } else if(args[0].equalsIgnoreCase("edit")){
                list.add("displayname");
                list.add("color");
            } else if(args[0].equalsIgnoreCase("list")){
                if(args[1].equalsIgnoreCase("team"))
                    return TeamsFile.getConfig().getStringList("teamsList");
            } else if(args[0].equalsIgnoreCase("whereis")){
                if(args[1].equalsIgnoreCase("player"))
                    return null;
                else if(args[1].equalsIgnoreCase("color"))
                    return getColorsList();
            }
        } else if(args.length == 4){
            if(args[0].equalsIgnoreCase("edit")){
                if(args[2].equalsIgnoreCase("color")){
                    list = getColorsList();
                    list.removeAll(TeamsFile.getConfig().getStringList("takenColors"));
                }
            } else if(args[0].equalsIgnoreCase("create")){
                list = getColorsList();
                list.removeAll(TeamsFile.getConfig().getStringList("takenColors"));
            }
        }


        return list;
    }

    private static List<String> getColorsList() {
        List<String> list = new ArrayList<>();

        list.add("BLACK");
        list.add("DARK_BLUE");
        list.add("DARK_GREEN");
        list.add("DARK_AQUA");
        list.add("DARK_RED");
        list.add("DARK_PURPLE");
        list.add("GOLD");
        list.add("GRAY");
        list.add("DARK_GRAY");
        list.add("BLUE");
        list.add("GREEN");
        list.add("AQUA");
        list.add("RED");
        list.add("LIGHT_PURPLE");
        list.add("YELLOW");
        list.add("WHITE");

        return list;
    }
}