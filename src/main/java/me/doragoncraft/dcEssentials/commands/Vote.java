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

public class Vote implements CommandExecutor {
    private final Dcessentails plugin;

    public Vote(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection cs = config.getConfigurationSection("Vote");

        if (cs == null) {
            p.sendMessage(ChatColor.RED + "Vote configuration section is missing.");
            return true;
        }

        if (args.length == 0) {
            if (cs.getBoolean("Enabled")) {
                // Replace placeholders with nice Unicode icons or emojis
                String msg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.maintitle", "")
                        .replace("{icon1}", "⭐")   // star
                        .replace("{icon2}", "⚡")   // lightning
                        .replace("{icon3}", "🔥")   // fire
                        .replace("{icon4}", "🎉")   // party popper
                        .replace("{icon5}", "💎")); // gem

                String submsg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.subtitle", "")
                        .replace("{icon1}", "⭐")
                        .replace("{icon2}", "⚡")
                        .replace("{icon3}", "🔥")
                        .replace("{icon4}", "🎉")
                        .replace("{icon5}", "💎"));

                for (String line : cs.getStringList("Message")) {
                    p.sendMessage(ChatUti.format(line));
                }
                p.sendTitle(msg, submsg, config.getInt("fadein"), config.getInt("stay"), config.getInt("fadeout"));
                return true;
            } else {
                p.sendMessage(ChatColor.RED + "Please ask one of the admins to enable this option!");
                return true;
            }
        }

        p.sendMessage(ChatColor.RED + "Usage: /vote");
        return true;
    }
}
