package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.KitManager;
import me.doragoncraft.dcEssentials.uti.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KitCommand implements CommandExecutor, TabCompleter {

    private final KitManager kitManager;

    public KitCommand(Dcessentails plugin) {
        this.kitManager = plugin.getKitManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /kit <name>, /kit create <name> <delay>, /kit delete <name>, /kit preview <name>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "Usage: /kit create <name> <delaySeconds>");
                    return true;
                }
                String name = args[1];
                long delayMillis = Long.parseLong(args[2]) * 1000L;
                kitManager.createKit(name, Arrays.asList(player.getInventory().getContents()), delayMillis);
                player.sendMessage(ChatColor.GREEN + "Kit '" + name + "' created.");
                break;

            case "delete":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usage: /kit delete <name>");
                    return true;
                }
                kitManager.deleteKit(args[1]);
                player.sendMessage(ChatColor.GREEN + "Kit '" + args[1] + "' deleted.");
                break;

            case "preview":
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usage: /kit preview <name>");
                    return true;
                }
                Kit previewKit = kitManager.getKit(args[1]);
                if (previewKit == null) {
                    player.sendMessage(ChatColor.RED + "Kit not found.");
                    return true;
                }
                Inventory gui = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Kit: " + previewKit.getName());
                int index = 0;
                for (ItemStack item : previewKit.getItems()) {
                    if (item != null && item.getType() != Material.AIR) {
                        gui.setItem(index++, item);
                    }
                }
                player.openInventory(gui);
                break;

            default:
                Kit kit = kitManager.getKit(args[0]);
                if (kit == null) {
                    player.sendMessage(ChatColor.RED + "Kit not found.");
                    return true;
                }
                if (!kitManager.canClaim(player, kit)) {
                    long remaining = kitManager.getRemainingCooldown(player, kit) / 1000L;
                    player.sendMessage(ChatColor.RED + "You must wait " + remaining + " seconds before claiming this kit again.");
                    return true;
                }
                for (ItemStack item : kit.getItems()) {
                    if (item != null && item.getType() != Material.AIR) {
                        player.getInventory().addItem(item);
                    }
                }
                kitManager.setCooldown(player, kit);
                player.sendMessage(ChatColor.GREEN + "You claimed kit: " + kit.getName());
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("create", "delete", "preview").stream()
                    .filter(s -> s.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
