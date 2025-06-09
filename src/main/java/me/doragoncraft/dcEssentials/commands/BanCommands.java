package me.doragoncraft.dcEssentials.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BanCommands implements CommandExecutor {

    private final File banFile;
    private final FileConfiguration banConfig;

    public BanCommands() {
        banFile = new File("plugins/DcEssentials", "bans.yml");
        if (!banFile.exists()) {
            try {
                banFile.getParentFile().mkdirs();
                banFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        banConfig = YamlConfiguration.loadConfiguration(banFile);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (args.length < 2) {
                sender.sendMessage("Usage: /ban <player> <reason>");
                return true;
            }

            String targetName = args[0];
            String reason = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

            Player target = Bukkit.getPlayerExact(targetName);
            if (target == null) {
                sender.sendMessage("Player not found or offline.");
                return true;
            }

            banConfig.set("bans." + target.getUniqueId() + ".reason", reason);
            banConfig.set("bans." + target.getUniqueId() + ".expiry", -1L); // -1 = permanent ban

            try {
                banConfig.save(banFile);
                target.kickPlayer("You have been banned: " + reason);
                sender.sendMessage(targetName + " has been banned permanently.");
            } catch (IOException e) {
                sender.sendMessage("Failed to save ban.");
                e.printStackTrace();
            }

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tempban")) {
            if (args.length < 3) {
                sender.sendMessage("Usage: /tempban <player> <time> <reason>");
                return true;
            }

            String targetName = args[0];
            String timeString = args[1];
            String reason = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length));

            Player target = Bukkit.getPlayerExact(targetName);
            if (target == null) {
                sender.sendMessage("Player not found or offline.");
                return true;
            }

            long expiry = System.currentTimeMillis() + parseTime(timeString);
            if (expiry <= System.currentTimeMillis()) {
                sender.sendMessage("Invalid time format.");
                return true;
            }

            banConfig.set("bans." + target.getUniqueId() + ".reason", reason);
            banConfig.set("bans." + target.getUniqueId() + ".expiry", expiry);

            try {
                banConfig.save(banFile);
                target.kickPlayer("You have been temporarily banned for " + timeString + ": " + reason);
                sender.sendMessage(targetName + " has been temporarily banned for " + timeString + ".");
            } catch (IOException e) {
                sender.sendMessage("Failed to save tempban.");
                e.printStackTrace();
            }

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("unban")) {
            if (args.length != 1) {
                sender.sendMessage("Usage: /unban <player>");
                return true;
            }

            String targetName = args[0];
            // We need UUID to remove ban, get offline player:
            OfflinePlayer offline = Bukkit.getOfflinePlayer(targetName);
            UUID uuid = offline.getUniqueId();

            if (!banConfig.contains("bans." + uuid)) {
                sender.sendMessage("Player is not banned.");
                return true;
            }

            banConfig.set("bans." + uuid, null);

            try {
                banConfig.save(banFile);
                sender.sendMessage(targetName + " has been unbanned.");
            } catch (IOException e) {
                sender.sendMessage("Failed to save unban.");
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }

    private long parseTime(String timeString) {
        // Example time formats: 1d, 2h, 30m, 10s
        long totalMillis = 0;
        Pattern pattern = Pattern.compile("(\\d+)([dhms])");
        Matcher matcher = pattern.matcher(timeString.toLowerCase());

        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);
            switch (unit) {
                case "d": totalMillis += value * 86400000L; break; // days to ms
                case "h": totalMillis += value * 3600000L; break;  // hours to ms
                case "m": totalMillis += value * 60000L; break;    // minutes to ms
                case "s": totalMillis += value * 1000L; break;     // seconds to ms
            }
        }
        return totalMillis;
    }
}
