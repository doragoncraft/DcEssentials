package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.managers.HomeManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    private final HomeManager homeManager;

    public HomeCommand(HomeManager homeManager) {
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

        Location home = homeManager.getHome(player, homeName);
        if (home == null) {
            player.sendMessage("§cHome §e" + homeName + " §cnot found!");
            return true;
        }

        player.teleport(home);
        player.sendMessage("§aTeleported to home §e" + homeName + "§a!");
        return true;
    }
}
