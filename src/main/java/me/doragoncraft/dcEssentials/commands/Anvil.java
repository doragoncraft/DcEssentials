package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class Anvil implements CommandExecutor {

    private final Dcessentails plugin;

    public Anvil(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.CONSOLE.get());
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("decp.anvil")) {
            player.sendMessage(ChatColor.RED + Lang.NO_PERM.get());
            return true;
        }

        player.openInventory(Bukkit.createInventory(null, InventoryType.ANVIL, ChatColor.GOLD + "Anvil"));
        return true;
    }
}
