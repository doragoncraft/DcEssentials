package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphereCommand implements CommandExecutor {

    public TphereCommand(Dcessentails dcessentails) {
        // No need for plugin reference here
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color("&cOnly players can use this command!"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("decp.tphere")) {
            player.sendMessage(color("&cYou don't have permission to use this command."));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(color("&cUsage: /tphere <player>"));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage(color("&cPlayer not found or not online."));
            return true;
        }

        if (target.equals(player)) {
            player.sendMessage(color("&cYou cannot teleport yourself."));
            return true;
        }

        target.teleport(player.getLocation());
        target.sendMessage(color("&aYou have been teleported to &e" + player.getName() + "&a."));
        player.sendMessage(color("&aTeleported &e" + target.getName() + " &ato your location."));

        return true;
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
