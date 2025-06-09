package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class YouTubeCmd implements CommandExecutor {

    private final Dcessentails plugin;

    public YouTubeCmd(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
        Player p = (Player) sender;

        ConfigurationSection cs = plugin.getConfig().getConfigurationSection("Youtube");
        if (cs == null) {
            p.sendMessage(ChatColor.RED + "YouTube configuration not found!");
            return true;
        }

        if (!cs.getBoolean("Enabled", false)) {
            p.sendMessage(ChatColor.RED + "The /youtube command is currently disabled in the config.");
            return true;
        }

        String msg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.maintitle", "")
                .replace("{icon1}", "▶")  // example icons, customize as needed
                .replace("{icon2}", "📺")
                .replace("{icon3}", "⭐")
                .replace("{icon4}", "🔥")
                .replace("{icon5}", "👍"));

        String subtitle = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.subtitle", "")
                .replace("{icon1}", "▶")
                .replace("{icon2}", "📺")
                .replace("{icon3}", "⭐")
                .replace("{icon4}", "🔥")
                .replace("{icon5}", "👍"));

        for (String line : cs.getStringList("Message")) {
            p.sendMessage(ChatUti.format(line).replace("%player%", p.getName()));
        }

        int fadeIn = plugin.getConfig().getInt("fadein", 10);
        int stay = plugin.getConfig().getInt("stay", 70);
        int fadeOut = plugin.getConfig().getInt("fadeout", 20);

        p.sendTitle(msg, subtitle, fadeIn, stay, fadeOut);

        return true;
    }
}
