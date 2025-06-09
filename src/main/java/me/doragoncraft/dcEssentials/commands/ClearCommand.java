package me.doragoncraft.dcEssentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements CommandExecutor, TabCompleter {

    public ClearCommand() {
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && args.length == 0) {
            sender.sendMessage(color("&cConsole must specify a player."));
            return true;
        }

        // Clear own inventory
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(color("&cConsole must specify a player."));
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("decp.clear")) {
                player.sendMessage(color("&cYou don't have permission to use this command."));
                return true;
            }

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.sendMessage(color("&aYour inventory has been cleared."));
            return true;
        }

        // Clear other player's inventory
        if (args.length == 1) {
            if (!sender.hasPermission("decp.clear.other")) {
                sender.sendMessage(color("&cYou don't have permission to clear other players' inventories."));
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                sender.sendMessage(color("&cPlayer not found."));
                return true;
            }

            target.getInventory().clear();
            target.getInventory().setArmorContents(null);
            target.sendMessage(color("&cYour inventory has been cleared by &e" + sender.getName() + "&c."));
            sender.sendMessage(color("&aCleared &e" + target.getName() + "&a's inventory."));
            return true;
        }

        sender.sendMessage(color("&cUsage: /clear [player]"));
        return true;
    }

    // Tab Completion
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                completions.add(p.getName());
            }
        }
        return completions;
    }
}
