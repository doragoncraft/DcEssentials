package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.TeleportRequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {

    private final TeleportRequestManager requestManager;

    public TpaCommand(TeleportRequestManager requestManager, Dcessentails dcessentails) {
        this.requestManager = requestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.GRAY + "Usage: /tpa <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage(ChatColor.RED + "Player not found or not online!");
            return true;
        }

        if (target.equals(player)) {
            player.sendMessage(ChatColor.RED + "You cannot send a teleport request to yourself!");
            return true;
        }

        if (requestManager.isToggledOff(target)) {
            player.sendMessage(ChatColor.RED + "That player is not accepting teleport requests.");
            return true;
        }

        if (requestManager.hasRequest(target)) {
            player.sendMessage(ChatColor.RED + "That player already has a pending teleport request.");
            return true;
        }

        requestManager.addRequest(player, target);

        target.sendMessage(ChatColor.GREEN + player.getName() + " has requested to teleport to you.");
        target.sendMessage(ChatColor.GREEN + "Type /tpaccept to accept or /tpdeny to deny.");
        player.sendMessage(ChatColor.GREEN + "Teleport request sent to " + target.getName());

        // Optional: schedule expiration after 120 seconds (can add Bukkit runnable here)

        return true;
    }
}
