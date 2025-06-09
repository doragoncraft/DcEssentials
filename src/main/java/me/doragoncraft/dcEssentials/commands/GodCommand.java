package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.GodManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
    private final Dcessentails plugin;
    private final GodManager godManager;

    public GodCommand(Dcessentails plugin) {
        this.plugin = plugin;
        this.godManager = plugin.getGodManager(); // make sure to add getter in main
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission("decp.god")) {
                player.sendMessage("You don't have permission.");
                return true;
            }
            boolean enabled = godManager.toggleGod(player);
            player.sendMessage(enabled ? "God mode enabled." : "God mode disabled.");
            return true;
        }

        if (args.length == 1) {
            if (!player.hasPermission("decp.god.others")) {
                player.sendMessage("You don't have permission to toggle god mode for others.");
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage("Player not found.");
                return true;
            }

            boolean enabled = godManager.toggleGod(target);
            target.sendMessage(enabled ? "God mode enabled." : "God mode disabled.");
            player.sendMessage((enabled ? "Enabled" : "Disabled") + " god mode for " + target.getName() + ".");
            return true;
        }

        player.sendMessage("Usage: /god [player]");
        return true;
    }
}
