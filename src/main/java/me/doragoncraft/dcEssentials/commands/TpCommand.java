package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    public TpCommand(Dcessentails plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null || !target.isOnline()) {
                player.sendMessage(ChatColor.RED + "Player not found or not online!");
                return true;
            }
            player.teleport(target.getLocation());
            player.sendMessage(ChatColor.GREEN + "Teleported to " + target.getName());
            return true;

        } else if (args.length == 2) {
            Player from = Bukkit.getPlayerExact(args[0]);
            Player to = Bukkit.getPlayerExact(args[1]);

            if (from == null || !from.isOnline()) {
                player.sendMessage(ChatColor.RED + "Player '" + args[0] + "' not found or not online!");
                return true;
            }
            if (to == null || !to.isOnline()) {
                player.sendMessage(ChatColor.RED + "Player '" + args[1] + "' not found or not online!");
                return true;
            }

            from.teleport(to.getLocation());
            from.sendMessage(ChatColor.GREEN + "You have been teleported to " + to.getName());
            if (!from.equals(player)) {
                player.sendMessage(ChatColor.GREEN + "Teleported " + from.getName() + " to " + to.getName());
            }
            return true;

        } else {
            player.sendMessage(ChatColor.GRAY + "Usage: /tp <target> OR /tp <player> <target>");
            return true;
        }
    }
}
