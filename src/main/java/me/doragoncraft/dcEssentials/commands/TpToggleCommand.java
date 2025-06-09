package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.TeleportRequestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpToggleCommand implements CommandExecutor {

    private final TeleportRequestManager requestManager;

    public TpToggleCommand(TeleportRequestManager requestManager, Dcessentails dcessentails) {
        this.requestManager = requestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        requestManager.toggle(player);

        if (requestManager.isToggledOff(player)) {
            player.sendMessage(ChatColor.RED + "You are no longer accepting teleport requests.");
        } else {
            player.sendMessage(ChatColor.GREEN + "You are now accepting teleport requests.");
        }

        return true;
    }
}
