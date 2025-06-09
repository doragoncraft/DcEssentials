package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.SpeedManager;
import me.doragoncraft.dcEssentials.uti.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {
    private final Dcessentails plugin;
    private final SpeedManager speedManager;

    public SpeedCommand(Dcessentails plugin) {
        this.plugin = plugin;
        this.speedManager = new SpeedManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String noPlayerMsg = Lang.CONSOLE.get();
        String noPermMsg = Lang.NO_PERM.get();
        String usageMsg = "Usage: /speed <walk|fly> <0-10> [player]";

        if (!(sender instanceof Player)) {
            sender.sendMessage(noPlayerMsg);
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage(usageMsg);
            return true;
        }

        String type = args[0].toLowerCase();
        String speedStr = args[1];
        Player target = player;

        // Parse speed as int 0-10
        int speedValue;
        try {
            speedValue = Integer.parseInt(speedStr);
            if (speedValue < 0 || speedValue > 10) {
                player.sendMessage("Speed must be between 0 and 10.");
                return true;
            }
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid speed. Must be a number between 0 and 10.");
            return true;
        }

        // Check if target player specified
        if (args.length >= 3) {
            if (!player.hasPermission("decp.speed.others")) {
                player.sendMessage(noPermMsg);
                return true;
            }
            target = Bukkit.getPlayerExact(args[2]);
            if (target == null) {
                player.sendMessage("Player not online.");
                return true;
            }
        } else {
            // Changing own speed requires this permission
            if (!player.hasPermission("decp.speed")) {
                player.sendMessage(noPermMsg);
                return true;
            }
        }

        // Convert 0-10 to 0.0-1.0 float for Bukkit
        float bukkitSpeed = speedValue / 10f;

        switch (type) {
            case "walk":
                speedManager.setWalkSpeed(target, bukkitSpeed);
                sendSpeedMessage(player, target, "walk", speedValue);
                break;

            case "fly":
                speedManager.setFlySpeed(target, bukkitSpeed);
                sendSpeedMessage(player, target, "fly", speedValue);
                break;

            default:
                player.sendMessage("Invalid type! Use walk or fly.");
                return true;
        }

        return true;
    }

    private void sendSpeedMessage(Player sender, Player target, String speedType, int speedValue) {
        if (sender.equals(target)) {
            sender.sendMessage("Your " + speedType + " speed has been set to " + speedValue + ".");
        } else {
            sender.sendMessage(target.getName() + "'s " + speedType + " speed has been set to " + speedValue + ".");
            target.sendMessage("Your " + speedType + " speed has been set to " + speedValue + " by " + sender.getName() + ".");
        }
    }
}
