package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCMD implements CommandExecutor {

    private final Dcessentails plugin;

    public VanishCMD(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String noPlayer = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Command Messages.No Player", "Only players can execute this command."));
        String noPerm = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Command Messages.No Permissions", "You do not have permission to use this command."));
        String vanishedMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Vanish.Vanished", "You are now vanished."));
        String unvanishedMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Vanish.Un-Vanished", "You are now visible."));

        if (!(sender instanceof Player)) {
            sender.sendMessage(noPlayer);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("decp.vanish")) {
            player.sendMessage(noPerm);
            return true;
        }

        if (!plugin.getVanished().contains(player)) {
            // Vanish player
            plugin.getVanished().add(player);
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.hasPermission("decp.vanish.see")) {
                    online.hidePlayer(plugin, player);
                } else {
                    online.showPlayer(plugin, player);
                }
            }
            player.sendMessage(vanishedMsg);
        } else {
            // Unvanish player
            plugin.getVanished().remove(player);
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, player);
            }
            player.sendMessage(unvanishedMsg);
        }
        return true;
    }
}
