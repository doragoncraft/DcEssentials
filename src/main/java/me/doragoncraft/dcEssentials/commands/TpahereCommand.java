package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.TeleportRequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpahereCommand implements CommandExecutor {

    private final TeleportRequestManager teleportRequestManager;

    public TpahereCommand(TeleportRequestManager teleportRequestManager, Dcessentails dcessentails) {
        this.teleportRequestManager = teleportRequestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player senderPlayer = (Player) sender;

        // Permission check
        if (!senderPlayer.hasPermission("decp.tpahere")) {
            senderPlayer.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            senderPlayer.sendMessage(ChatColor.RED + "Usage: /tpahere <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null || !target.isOnline()) {
            senderPlayer.sendMessage(ChatColor.RED + "Player not found or not online.");
            return true;
        }

        if (target.equals(senderPlayer)) {
            senderPlayer.sendMessage(ChatColor.RED + "You cannot send a teleport request to yourself.");
            return true;
        }

        // Register teleport request: sender requests target to teleport to sender
        teleportRequestManager.addRequest(senderPlayer, target);

        senderPlayer.sendMessage(ChatColor.GREEN + "Teleport request sent to " + target.getName());
        target.sendMessage(ChatColor.GREEN + senderPlayer.getName() + " has requested you to teleport to them. Type /tpaccept to accept.");

        return true;
    }
}

