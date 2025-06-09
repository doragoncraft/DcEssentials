package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerIP implements CommandExecutor {

    private final Dcessentails plugin;

    public ServerIP(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        // Replace corrupted icons with valid symbols or emojis
        String icon1 = "★";
        String icon2 = "✦";
        String icon3 = "✪";
        String icon4 = "❂";
        String icon5 = "❀";

        String msg = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("STitle", "")
                .replace("{icon1}", icon1)
                .replace("{icon2}", icon2)
                .replace("{icon3}", icon3)
                .replace("{icon4}", icon4)
                .replace("{icon5}", icon5));

        String subtitle = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("SSubTitle", "")
                .replace("{icon1}", icon1)
                .replace("{icon2}", icon2)
                .replace("{icon3}", icon3)
                .replace("{icon4}", icon4)
                .replace("{icon5}", icon5));

        if (args.length == 0) {
            if (this.plugin.getConfig().getBoolean("ServerIP Enabled", false)) {
                for (String line : this.plugin.getConfig().getStringList("ServerIP")) {
                    p.sendMessage(ChatUti.format(line).replace("%player%", p.getName()));
                }
                p.sendTitle(msg, subtitle,
                        this.plugin.getConfig().getInt("fadein"),
                        this.plugin.getConfig().getInt("stay"),
                        this.plugin.getConfig().getInt("fadeout"));
            } else {
                p.sendMessage(ChatColor.RED + "Please enable /IP from the config!");
            }
            return true;
        }

        // Handle subcommands
        if ("Server".equalsIgnoreCase(args[0])) {
            for (String line : this.plugin.getConfig().getStringList("Server")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
            }
        } else {
            p.sendMessage(ChatColor.RED + "Unknown argument. Usage: /ip [Server]");
        }

        return true;
    }
}
