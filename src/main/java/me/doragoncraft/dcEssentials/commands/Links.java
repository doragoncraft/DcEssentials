package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Links implements CommandExecutor {

    private final Dcessentails plugin;

    public Links(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        Player p = (Player) sender;

        ConfigurationSection cs = plugin.getConfig().getConfigurationSection("Links");
        if (cs == null) {
            p.sendMessage(ChatColor.RED + "Links section is not configured.");
            return true;
        }

        // Replace corrupted icons with valid ones
        String icon1 = "★";
        String icon2 = "✦";
        String icon3 = "✪";
        String icon4 = "❂";
        String icon5 = "❀";

        String msg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.maintitle", "")
                .replace("{icon1}", icon1)
                .replace("{icon2}", icon2)
                .replace("{icon3}", icon3)
                .replace("{icon4}", icon4)
                .replace("{icon5}", icon5));

        String subtitle = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.subtitle", "")
                .replace("{icon1}", icon1)
                .replace("{icon2}", icon2)
                .replace("{icon3}", icon3)
                .replace("{icon4}", icon4)
                .replace("{icon5}", icon5));

        if (args.length == 0) {
            if (cs.getBoolean("Enabled", false)) {
                for (String line : cs.getStringList("Message")) {
                    p.sendMessage(ChatUti.format(line));
                }
                p.sendTitle(msg, subtitle,
                        plugin.getConfig().getInt("fadein"),
                        plugin.getConfig().getInt("stay"),
                        plugin.getConfig().getInt("fadeout"));
            } else {
                p.sendMessage(ChatColor.RED + "Please enable /links in the config.");
            }
            return true;
        }

        // Optionally add support for subcommands or arguments here

        p.sendMessage(ChatColor.RED + "Incorrect usage. Use /links without arguments.");
        return true;
    }
}
