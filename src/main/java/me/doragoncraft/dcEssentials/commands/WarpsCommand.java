package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.managers.WarpsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;

public class WarpsCommand implements CommandExecutor {

    private final WarpsManager warpsManager;

    public WarpsCommand(WarpsManager warpsManager) {
        this.warpsManager = warpsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        Set<String> warps = warpsManager.getWarps();

        if (warps.isEmpty()) {
            player.sendMessage(ChatColor.RED + "There are no warps set.");
            return true;
        }

        // Filter warps player has permission to use:
        Set<String> accessibleWarps = warps.stream()
                .filter(warp -> player.hasPermission("dceasycmds.warp.*") // wildcard perm with '*'
                        || player.hasPermission("dceasycmds.warp." + warp.toLowerCase()))
                .collect(Collectors.toSet());

        if (accessibleWarps.isEmpty()) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use any warps.");
            return true;
        }

        // Build message with warp names separated by commas and colored green
        String warpList = accessibleWarps.stream()
                .map(warp -> ChatColor.GREEN + warp)
                .collect(Collectors.joining(ChatColor.WHITE + ", "));

        player.sendMessage(ChatColor.YELLOW + "Available warps: " + warpList);

        return true;
    }
}
