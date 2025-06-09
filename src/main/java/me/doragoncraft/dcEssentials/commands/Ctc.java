package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Ctc implements CommandExecutor {

    private final Dcessentails plugin;

    public Ctc(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;
        FileConfiguration config = plugin.getConfig();

        String message = color(config.getString("Message"));
        String prefix = color(config.getString("Prefix"));
        String linesMsg = config.getString("Lines");
        String broadcastMsg = color(config.getString("CMessage"));
        int lines = config.getInt("Lines");

        if (args.length == 0) {
            if (p.hasPermission("decp.ctc.clear")) {
                for (int i = 0; i < lines; i++) {
                    Bukkit.broadcastMessage(broadcastMsg);
                }
                Bukkit.broadcastMessage(prefix + " " + p.getName() + " " + ChatColor.GRAY + message);
            } else {
                p.sendMessage(Lang.NO_PERM.get());
            }
            return true;
        }

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "reload":
                    if (p.hasPermission("decp.ctc.reload")) {
                        plugin.reloadConfig();
                        p.sendMessage(Lang.RELOAD.get());
                    } else {
                        p.sendMessage(Lang.NO_PERM.get());
                    }
                    break;

                case "lines":
                    if (p.hasPermission("decp.ctc.lines")) {
                        p.sendMessage(ChatColor.RED + "Lines: " + linesMsg);
                    } else {
                        p.sendMessage(Lang.NO_PERM.get());
                    }
                    break;

                case "help":
                    p.sendMessage(ChatColor.DARK_GRAY + "===============================");
                    p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/ctc help - This menu.");
                    p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/ctc lines - Show number of lines.");
                    p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/ctc personal - Clear your own chat.");
                    p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.RED + "/ctc - Clear everyone's chat.");
                    p.sendMessage(ChatColor.GREEN + "Created by: " + ChatColor.RED + "DoragonCraft");
                    p.sendMessage(ChatColor.DARK_GRAY + "===============================");
                    break;

                case "personal":
                    if (p.hasPermission("decp.ctc.personal")) {
                        for (int i = 0; i < lines; i++) {
                            p.sendMessage("");
                        }
                        p.sendMessage(prefix + " " + ChatColor.RED + "You cleared your chat!");
                    } else {
                        p.sendMessage(Lang.NO_PERM.get());
                    }
                    break;

                default:
                    p.sendMessage(ChatColor.RED + "Unknown argument. Use /ctc help.");
                    break;
            }
            return true;
        }

        // If too many arguments
        p.sendMessage(ChatColor.RED + "Incorrect usage. Use /ctc help.");
        return true;
    }

    private String color(String input) {
        if (input == null) return "";
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
