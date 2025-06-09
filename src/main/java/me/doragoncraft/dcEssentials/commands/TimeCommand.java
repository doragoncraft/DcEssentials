package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeCommand implements CommandExecutor, TabCompleter {

    private final String prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "Time" + ChatColor.GRAY + "] ";

    public TimeCommand(Dcessentails plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world;

        if (sender instanceof Player) {
            world = ((Player) sender).getWorld();
        } else {
            world = Bukkit.getWorlds().get(0);
        }

        String cmd = label.toLowerCase();

        if (cmd.equals("day")) {
            setTime(world, 1000, sender, "Day");
            return true;
        }
        if (cmd.equals("night")) {
            setTime(world, 13000, sender, "Night");
            return true;
        }
        if (cmd.equals("noon")) {
            setTime(world, 6000, sender, "Noon");
            return true;
        }
        if (cmd.equals("midnight")) {
            setTime(world, 18000, sender, "Midnight");
            return true;
        }
        if (cmd.equals("sunrise")) {
            setTime(world, 23000, sender, "Sunrise");
            return true;
        }
        if (cmd.equals("sunset")) {
            setTime(world, 12000, sender, "Sunset");
            return true;
        }

        if (cmd.equals("time")) {
            if (args.length != 1) {
                sender.sendMessage(prefix + ChatColor.RED + "Usage: /time <day|night|noon|midnight|sunrise|sunset>");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "day":
                    setTime(world, 1000, sender, "Day");
                    break;
                case "night":
                    setTime(world, 13000, sender, "Night");
                    break;
                case "noon":
                    setTime(world, 6000, sender, "Noon");
                    break;
                case "midnight":
                    setTime(world, 18000, sender, "Midnight");
                    break;
                case "sunrise":
                    setTime(world, 23000, sender, "Sunrise");
                    break;
                case "sunset":
                    setTime(world, 12000, sender, "Sunset");
                    break;
                default:
                    sender.sendMessage(prefix + ChatColor.RED + "Invalid time. Use: day, night, noon, midnight, sunrise, or sunset.");
                    break;
            }
            return true;
        }

        return false;
    }

    private void setTime(World world, long time, CommandSender sender, String timeName) {
        world.setTime(time);
        sender.sendMessage(prefix + ChatColor.GREEN + "Time set to " + ChatColor.YELLOW + timeName + ChatColor.GREEN + ".");
    }

    // TAB COMPLETER
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("time")) {
            if (args.length == 1) {
                List<String> options = Arrays.asList("day", "night", "noon", "midnight", "sunrise", "sunset");
                List<String> completions = new ArrayList<>();
                String current = args[0].toLowerCase();
                for (String opt : options) {
                    if (opt.startsWith(current)) {
                        completions.add(opt);
                    }
                }
                return completions;
            }
        }
        return null;
    }
}
