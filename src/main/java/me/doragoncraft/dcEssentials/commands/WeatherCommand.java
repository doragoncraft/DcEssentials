package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

    public WeatherCommand(Dcessentails plugin) {
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private void broadcastToStaff(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("decp.weather.notify")) {
                p.sendMessage(message);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        World world = Bukkit.getWorlds().get(0); // Main world

        if (!sender.hasPermission("decp.weather")) {
            sender.sendMessage(color("&cYou do not have permission to use this command."));
            return true;
        }

        String cmd = command.getName().toLowerCase();

        // Shorthand commands
        switch (cmd) {
            case "sun":
            case "clear":
                world.setStorm(false);
                world.setThundering(false);
                sender.sendMessage(color("&aWeather set to clear."));
                broadcastToStaff(color("&e[Weather] &f" + sender.getName() + " set weather to &aClear."));
                return true;

            case "rain":
            case "storm":
                world.setStorm(true);
                world.setThundering(false);
                sender.sendMessage(color("&bWeather set to rain."));
                broadcastToStaff(color("&e[Weather] &f" + sender.getName() + " set weather to &bRain."));
                return true;

            case "thunder":
                world.setStorm(true);
                world.setThundering(true);
                sender.sendMessage(color("&eWeather set to thunderstorm."));
                broadcastToStaff(color("&e[Weather] &f" + sender.getName() + " set weather to &6Thunderstorm."));
                return true;

            case "weather":
                if (args.length < 1) {
                    sender.sendMessage(color("&cUsage: /weather <clear/sun/rain/storm/thunder>"));
                    return true;
                }

                String option = args[0].toLowerCase();
                switch (option) {
                    case "clear":
                    case "sun":
                        world.setStorm(false);
                        world.setThundering(false);
                        sender.sendMessage(color("&aWeather set to clear."));
                        broadcastToStaff(color("&e[Weather] &f" + sender.getName() + " set weather to &aClear."));
                        break;

                    case "rain":
                    case "storm":
                        world.setStorm(true);
                        world.setThundering(false);
                        sender.sendMessage(color("&bWeather set to rain."));
                        broadcastToStaff(color("&e[Weather] &f" + sender.getName() + " set weather to &bRain."));
                        break;

                    case "thunder":
                        world.setStorm(true);
                        world.setThundering(true);
                        sender.sendMessage(color("&eWeather set to thunderstorm."));
                        broadcastToStaff(color("&e[Weather] &f" + sender.getName() + " set weather to &6Thunderstorm."));
                        break;

                    default:
                        sender.sendMessage(color("&cInvalid weather type. Use clear/sun, rain/storm, or thunder."));
                        break;
                }
                return true;
        }

        return false;
    }
}
