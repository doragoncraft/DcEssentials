package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.GamemodeManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    private final GamemodeManager gamemodeManager;
    private final Dcessentails plugin;

    public GamemodeCommand(Dcessentails plugin) {
        this.plugin = plugin;
        this.gamemodeManager = new GamemodeManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(color("&cConsole must specify a player."));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(color("&cUsage: /gamemode <mode> [player]"));
            return true;
        }

        // Determine target
        Player target;
        if (args.length >= 2) {
            target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                sender.sendMessage(color("&cPlayer not found."));
                return true;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(color("&cConsole must specify a player."));
                return true;
            }
            target = (Player) sender;
        }

        // Permission checks
        if (sender != target && !sender.hasPermission("decp.gamemode.other")) {
            sender.sendMessage(color("&cYou don't have permission to change other players' gamemodes."));
            return true;
        }
        if (sender == target && !sender.hasPermission("decp.gamemode")) {
            sender.sendMessage(color("&cYou don't have permission to use this command."));
            return true;
        }

        // Parse gamemode
        GameMode mode = parseGamemode(args[0]);
        if (mode == null) {
            sender.sendMessage(color("&cInvalid gamemode. Use 0/1/2/3 or survival/creative/adventure/spectator."));
            return true;
        }

        // Set gamemode using manager
        gamemodeManager.setGamemode(target, mode);

        if (sender.equals(target)) {
            sender.sendMessage(color("&aYour gamemode is now &e" + capitalize(mode.name())));
        } else {
            sender.sendMessage(color("&aSet &e" + target.getName() + "&a's gamemode to &e" + capitalize(mode.name())));
            target.sendMessage(color("&aYour gamemode was set to &e" + capitalize(mode.name()) + "&a by &e" + sender.getName()));
        }

        return true;
    }

    private GameMode parseGamemode(String input) {
        switch (input.toLowerCase()) {
            case "0":
            case "survival":
            case "s": return GameMode.SURVIVAL;
            case "1":
            case "creative":
            case "c": return GameMode.CREATIVE;
            case "2":
            case "adventure":
            case "a": return GameMode.ADVENTURE;
            case "3":
            case "spectator":
            case "sp": return GameMode.SPECTATOR;
            default: return null;
        }
    }

    private String capitalize(String str) {
        if (str.isEmpty()) return "";
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    // TAB COMPLETION
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.addAll(Arrays.asList("0", "1", "2", "3", "survival", "creative", "adventure", "spectator"));
        }

        if (args.length == 2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                completions.add(p.getName());
            }
        }

        return completions;
    }
}
