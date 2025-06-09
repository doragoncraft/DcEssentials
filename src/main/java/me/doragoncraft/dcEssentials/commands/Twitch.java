package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Twitch implements CommandExecutor {
    Dcessentails plugin;

    public Twitch(Dcessentails plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        FileConfiguration config = this.plugin.getConfig();
        ConfigurationSection cs = config.getConfigurationSection("Twitch");

        // Replace with actual emoji or Minecraft symbols you want
        String icon1 = "★";
        String icon2 = "✦";
        String icon3 = "✪";
        String icon4 = "❂";
        String icon5 = "❀";

        String msg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.maintitle")
                .replace("{icon1}", icon1)
                .replace("{icon2}", icon2)
                .replace("{icon3}", icon3)
                .replace("{icon4}", icon4)
                .replace("{icon5}", icon5));

        String submsg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.subtitle")
                .replace("{icon1}", icon1)
                .replace("{icon2}", icon2)
                .replace("{icon3}", icon3)
                .replace("{icon4}", icon4)
                .replace("{icon5}", icon5));

        if (args.length == 0) {
            if (cs.getBoolean("Enabled")) {
                for (String line : cs.getStringList("Message")) {
                    player.sendMessage(ChatUti.format(line));
                }
                player.sendTitle(msg, submsg,
                        this.plugin.getConfig().getInt("fadein"),
                        this.plugin.getConfig().getInt("stay"),
                        this.plugin.getConfig().getInt("fadeout"));
            } else {
                player.sendMessage(ChatColor.RED + "Please enable /twitch from config.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Incorrect Usage.");
        }

        return true;
    }
}
