package me.doragoncraft.dcEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpposCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 3) {
            player.sendMessage("Usage: /tppos <x> <y> <z>");
            return true;
        }

        try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);

            player.teleport(player.getWorld().getBlockAt((int) x, (int) y, (int) z).getLocation().add(0.5, 0, 0.5));
            player.sendMessage("Teleported to " + x + ", " + y + ", " + z);
        } catch (NumberFormatException e) {
            player.sendMessage("Coordinates must be numbers.");
        }

        return true;
    }
}
