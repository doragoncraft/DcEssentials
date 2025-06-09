package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCMD implements CommandExecutor {

    private final Dcessentails plugin;

    public SpawnCMD(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color("&cOnly players can use this command!"));
            return true;
        }

        Player player = (Player) sender;

        switch (cmd.getName().toLowerCase()) {
            case "spawn":
                // No permission needed for /spawn as requested
                teleportToSpawn(player);
                return true;

            case "setspawn":
                if (!player.hasPermission("decp.setspawn")) {
                    player.sendMessage(color("&cYou don't have permission to use this command."));
                    return true;
                }
                setSpawn(player);
                return true;

            default:
                return false;
        }
    }

    private void teleportToSpawn(Player player) {
        FileConfiguration config = plugin.getConfig();

        if (!config.contains("spawn.world")) {
            player.sendMessage(color("&cSpawn has not been set yet!"));
            return;
        }

        World world = Bukkit.getWorld(config.getString("spawn.world"));
        if (world == null) {
            player.sendMessage(color("&cSpawn world does not exist!"));
            return;
        }

        double x = config.getDouble("spawn.x");
        double y = config.getDouble("spawn.y");
        double z = config.getDouble("spawn.z");
        float yaw = (float) config.getDouble("spawn.yaw");
        float pitch = (float) config.getDouble("spawn.pitch");

        Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
        player.teleport(spawnLocation);
        player.sendMessage(color("&aTeleported to spawn!"));
    }

    private void setSpawn(Player player) {
        Location loc = player.getLocation();
        FileConfiguration config = plugin.getConfig();

        config.set("spawn.world", loc.getWorld().getName());
        config.set("spawn.x", Double.valueOf(loc.getX()));
        config.set("spawn.y", Double.valueOf(loc.getY()));
        config.set("spawn.z", Double.valueOf(loc.getZ()));
        config.set("spawn.yaw", Float.valueOf(loc.getYaw()));
        config.set("spawn.pitch", Float.valueOf(loc.getPitch()));

        plugin.saveConfig();

        player.sendMessage(color("&aSpawn location set successfully!"));
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
