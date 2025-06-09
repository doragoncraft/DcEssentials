package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Discord
        implements CommandExecutor {
    Dcessentails plugin;
    String prefix;

    public Discord(Dcessentails plugin) {
        this.prefix = "Discord Help";
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target;
        ConfigurationSection cs = this.plugin.getConfig().getConfigurationSection("Discord");
        String msg = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.maintitle")
                .replace("{icon1}", "?")
                .replace("{icon2}", "?")
                .replace("{icon3}", "?(�?�)?")
                .replace("{icon4}", "?(?0�)?")
                .replace("{icon5}", "?(^o^)?"));
        String subtitle = ChatColor.translateAlternateColorCodes('&', cs.getString("Titles.subtitle")
                .replace("{icon1}", "?")
                .replace("{icon2}", "?")
                .replace("{icon3}", "?(�?�)?")
                .replace("{icon4}", "?(?0�)?")
                .replace("{icon5}", "?(^o^)?"));


        Player p = (Player)sender;
        if (args.length == 0) {
            if (cs.getBoolean("Enabled")) {
                for (int i = 0; i < cs.getStringList("Message").size(); i++) {
                    p.sendMessage(ChatUti.format((String)cs.getStringList("Message").get(i)));
                }

                p.sendTitle(msg, subtitle, this.plugin.getConfig().getInt("fadein"), this.plugin.getConfig().getInt("stay"), this.plugin.getConfig().getInt("fadeout"));
                return true;
            }
            if (args.length == 0) {
                p.sendMessage("Please Enable /Discord in the config!");
                return true;
            }
        }

        switch (args[0].toLowerCase())

        { case "about":
            p.sendMessage(ChatColor.GRAY + "-------------------------");
            p.sendMessage(ChatColor.AQUA + this.prefix + " " + ChatColor.GRAY + "Coded by: " + ChatColor.WHITE + "DoragonCraft");
            p.sendMessage(ChatColor.GRAY + "-------------------------");

            return true;case "player": target = Bukkit.getPlayer(args[0]); p.sendMessage("There isn't such a player!"); break; }  p.sendMessage(this.prefix + ChatColor.RED + " Something wen't wrong."); return true;
    }
}
