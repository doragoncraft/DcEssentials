package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.TeleportRequestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAcceptCommand implements CommandExecutor {

    private final TeleportRequestManager requestManager;

    public TpAcceptCommand(TeleportRequestManager requestManager, Dcessentails dcessentails) {
        this.requestManager = requestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player target = (Player) sender;

        if (!requestManager.hasRequest(target)) {
            target.sendMessage(ChatColor.GRAY + "You have no teleport requests to accept.");
            return true;
        }

        Player requester = requestManager.getRequester(target);

        if (requester == null || !requester.isOnline()) {
            target.sendMessage(ChatColor.RED + "The player who requested to teleport is no longer online.");
            requestManager.removeRequest(target);
            return true;
        }

        requester.teleport(target.getLocation());
        requester.sendMessage(ChatColor.GREEN + "You have been teleported to " + target.getName());
        target.sendMessage(ChatColor.GREEN + "You accepted the teleport request from " + requester.getName());

        requestManager.removeRequest(target);
        return true;
    }
}
