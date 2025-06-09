package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.managers.WarpsManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final WarpsManager warpsManager;

    public WarpCommand(WarpsManager warpsManager) {
        this.warpsManager = warpsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Only players can use warp
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("§cUsage: /warp <warpname>");
            return true;
        }

        String warpName = args[0].toLowerCase();

        // Permission check: example
        if (!player.hasPermission("dceasycmds.warp.*") && !player.hasPermission("dceasycmds.warp." + warpName)) {
            player.sendMessage("§cYou don't have permission to use this warp.");
            return true;
        }

        Location warpLocation = warpsManager.getWarp(warpName);

        if (warpLocation == null) {
            player.sendMessage("§cWarp '" + warpName + "' does not exist.");
            return true;
        }

        player.teleport(warpLocation);
        player.sendMessage("§aWarped to §e" + warpName + "§a.");

        return true;
    }
}
