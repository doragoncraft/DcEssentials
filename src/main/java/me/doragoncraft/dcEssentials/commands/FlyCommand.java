package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.FlyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final Dcessentails plugin;
    private final FlyManager flyManager;

    public FlyCommand(Dcessentails plugin) {
        this.plugin = plugin;
        this.flyManager = plugin.getFlyManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("decp.fly")) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        flyManager.toggleFlying(player);

        if (flyManager.isFlying(player)) {
            player.sendMessage("§aFlying enabled.");
        } else {
            player.sendMessage("§cFlying disabled.");
        }

        return true;
    }
}
