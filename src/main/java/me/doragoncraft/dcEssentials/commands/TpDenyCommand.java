package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.TeleportRequestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpDenyCommand implements CommandExecutor {

    private final TeleportRequestManager requestManager;

    public TpDenyCommand(TeleportRequestManager requestManager, Dcessentails dcessentails) {
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
            target.sendMessage(ChatColor.GRAY + "You have no teleport requests to deny.");
            return true;
        }

        Player requester = requestManager.getRequester(target);

        if (requester != null && requester.isOnline()) {
            requester.sendMessage(ChatColor.RED + target.getName() + " denied your teleport request.");
        }

        target.sendMessage(ChatColor.GREEN + "You denied the teleport request from " + (requester != null ? requester.getName() : "unknown player") + ".");
        requestManager.removeRequest(target);
        return true;
    }
}
