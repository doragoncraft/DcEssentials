package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final HomeManager homeManager;

    public SetHomeCommand(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        Player player = (Player) sender;
        String homeName = (args.length >= 1) ? args[0].toLowerCase() : "home";
        homeManager.setHome(player, homeName, player.getLocation());
        player.sendMessage("§aHome §e" + homeName + " §aset!");
        return true;
    }
}
