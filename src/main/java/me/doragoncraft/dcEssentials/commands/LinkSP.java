package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class LinkSP implements CommandExecutor {

    private final Dcessentails plugin;

    public LinkSP(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        List<String> messages = plugin.getConfig().getStringList("Messages.message");
        if (messages == null || messages.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No messages configured.");
            return true;
        }

        for (String msg : messages) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }

        return true;
    }
}
