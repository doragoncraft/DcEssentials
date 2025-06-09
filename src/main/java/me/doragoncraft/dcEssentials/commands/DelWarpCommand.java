package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.managers.WarpsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelWarpCommand implements CommandExecutor {

    private final WarpsManager warps;

    public DelWarpCommand(WarpsManager warps) {
        this.warps = warps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Permission check
        if (!sender.hasPermission("dceasycmds.warp.delete")) {
            sender.sendMessage("§cYou don't have permission to delete warps.");
            return true;
        }

        // Usage check
        if (args.length != 1) {
            sender.sendMessage("§eUsage: /delwarp <name>");
            return true;
        }

        String warpName = args[0];

        // Check if warp exists
        if (warps.getWarp(warpName) == null) {
            sender.sendMessage("§cThat warp doesn't exist.");
            return true;
        }

        // Delete warp
        warps.deleteWarp(warpName);
        sender.sendMessage("§aWarp §b" + warpName + "§a deleted.");
        return true;
    }
}
