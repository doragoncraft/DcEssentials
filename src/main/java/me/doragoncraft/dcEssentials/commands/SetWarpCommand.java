package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.managers.WarpsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private final WarpsManager warps;

    public SetWarpCommand(WarpsManager warps) {
        this.warps = warps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can set warps.");
            return true;
        }

        if (!sender.hasPermission("dceasycmds.warp.set")) {
            sender.sendMessage("§cYou don't have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§eUsage: /setwarp <name>");
            return true;
        }

        Player player = (Player) sender;
        warps.setWarp(args[0], player.getLocation());
        sender.sendMessage("§aWarp §b" + args[0] + "§a set!");
        return true;
    }
}
