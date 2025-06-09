package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class Whois implements CommandExecutor {
    private final Dcessentails plugin;
    private static final String PREFIX = "[" + ChatColor.GOLD + "PlayerIP" + ChatColor.WHITE + "] ";

    // DecimalFormat for formatting floats to 2 decimal places
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public Whois(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Permission check
        if (!sender.hasPermission("playerip.use")) {
            sender.sendMessage(PREFIX + ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length == 1) {
            String playerName = args[0];
            Player p = Bukkit.getPlayerExact(playerName);

            if (p == null) {
                sender.sendMessage(PREFIX + ChatColor.RED + "This player does not exist or is not connected.");
                return true;
            }

            // Send player info
            sender.sendMessage(PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "'s info:");
            sender.sendMessage(ChatColor.YELLOW + " UUID: " + ChatColor.GOLD + p.getUniqueId());
            sender.sendMessage(ChatColor.YELLOW + " IP address: " + ChatColor.GOLD + (p.getAddress() != null ? p.getAddress().getHostString() : "Unknown"));
            sender.sendMessage(ChatColor.YELLOW + " Gamemode: " + ChatColor.GOLD + p.getGameMode().toString());
            sender.sendMessage(ChatColor.YELLOW + " Health: " + ChatColor.GOLD + p.getHealth() + "/" + p.getMaxHealth());
            sender.sendMessage(ChatColor.YELLOW + " Food level: " + ChatColor.GOLD + p.getFoodLevel() + "/20");
            sender.sendMessage(ChatColor.YELLOW + " Experience: " + ChatColor.GOLD + df.format(p.getExp()));
            sender.sendMessage(ChatColor.YELLOW + " Level: " + ChatColor.GOLD + p.getLevel());
            sender.sendMessage(ChatColor.YELLOW + " Allow flight: " + ChatColor.GOLD + p.getAllowFlight());
            sender.sendMessage(ChatColor.YELLOW + " Flying: " + ChatColor.GOLD + p.isFlying());
            sender.sendMessage(ChatColor.YELLOW + " Fly speed: " + ChatColor.GOLD + df.format(p.getFlySpeed()));
            sender.sendMessage(ChatColor.YELLOW + " Walk speed: " + ChatColor.GOLD + df.format(p.getWalkSpeed()));
            p.getInventory().getItemInMainHand();
            sender.sendMessage(ChatColor.YELLOW + " Item in hand: " + ChatColor.GOLD +
                    p.getInventory().getItemInMainHand().getType().toString());
        } else {
            sender.sendMessage(PREFIX + ChatColor.RED + "Syntax: /playerip <username>");
        }

        return true;
    }
}
