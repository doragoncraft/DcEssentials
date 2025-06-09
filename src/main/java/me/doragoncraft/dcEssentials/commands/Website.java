package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Website implements CommandExecutor {
    private final Dcessentails plugin;

    public Website(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;
        ConfigurationSection cs = plugin.getConfig().getConfigurationSection("Website");
        if (cs == null) {
            p.sendMessage(ChatColor.RED + "Website configuration section not found.");
            return true;
        }

        String msg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.maintitle", "")
                .replace("{icon1}", "✦")
                .replace("{icon2}", "✧")
                .replace("{icon3}", "★")
                .replace("{icon4}", "☆")
                .replace("{icon5}", "✪"));

        String submsg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.subtitle", "")
                .replace("{icon1}", "✦")
                .replace("{icon2}", "✧")
                .replace("{icon3}", "★")
                .replace("{icon4}", "☆")
                .replace("{icon5}", "✪"));

        if (args.length == 0) {
            if (cs.getBoolean("Enabled", false)) {
                for (String line : cs.getStringList("Message")) {
                    p.sendMessage(ChatUti.format(line));
                }
                int fadeIn = plugin.getConfig().getInt("fadein", 10);
                int stay = plugin.getConfig().getInt("stay", 70);
                int fadeOut = plugin.getConfig().getInt("fadeout", 20);
                p.sendTitle(msg, submsg, fadeIn, stay, fadeOut);
            } else {
                p.sendMessage(ChatColor.RED + "Please ask one of the admins to enable this option!");
            }
            return true;
        }

        return false; // Return false if arguments are provided to show usage
    }
}
