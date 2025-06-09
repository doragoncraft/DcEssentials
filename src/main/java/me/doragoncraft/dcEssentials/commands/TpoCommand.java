package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpoCommand implements CommandExecutor {

    public TpoCommand(Dcessentails plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player executor = (Player) sender;

        if (!executor.hasPermission("decp.tpo")) {
            executor.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            executor.sendMessage(ChatColor.GRAY + "Usage: /tpo <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null || !target.isOnline()) {
            executor.sendMessage(ChatColor.RED + "Player not found or not online!");
            return true;
        }

        executor.teleport(target.getLocation());
        executor.sendMessage(ChatColor.GREEN + "Teleported to " + target.getName() + ".");
        return true;
    }
}
