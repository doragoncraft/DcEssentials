package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class StoreCmd implements CommandExecutor {

    private final Dcessentails plugin;

    public StoreCmd(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        ConfigurationSection cs = this.plugin.getConfig().getConfigurationSection("Store");
        if (cs == null) {
            p.sendMessage(ChatColor.RED + "Store configuration section is missing.");
            return true;
        }

        // Replace corrupted icons with valid Unicode symbols or emojis:
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
                    p.sendMessage(ChatUti.format(line).replace("%player%", p.getName()));
                }
                p.sendTitle(msg, subtitle,
                        this.plugin.getConfig().getInt("fadein"),
                        this.plugin.getConfig().getInt("stay"),
                        this.plugin.getConfig().getInt("fadeout"));
            } else {
                p.sendMessage(ChatColor.RED + "Something went wrong, is /store enabled?");
            }
            return true;
        }

        p.sendMessage(ChatColor.RED + "Incorrect usage. Use /store without arguments.");
        return true;
    }
}
