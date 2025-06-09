package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

    private final Dcessentails plugin;

    public Heal(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Safely get and translate color codes for messages
        String noPerm = ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("Command Messages.No Permissions", "&cYou do not have permission."));
        String feedMsg = ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("Health.FeedMsg", "&aYou have been fed."));
        String healMsg = ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("Health.HealMsg", "&aYou have been healed."));

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("feed")) {
            if (player.hasPermission("decp.feed")) {
                player.setFoodLevel(20);
                player.sendMessage(feedMsg);
            } else {
                player.sendMessage(noPerm);
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (player.hasPermission("decp.heal")) {
                player.setHealth(player.getMaxHealth()); // safer than hardcoded 20.0
                player.setFoodLevel(20);
                player.sendMessage(healMsg);
            } else {
                player.sendMessage(noPerm);
            }
            return true;
        }

        return false;
    }
}
