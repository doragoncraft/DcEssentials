package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class WrongCommand implements Listener {

    private final Dcessentails plugin;

    public WrongCommand(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWrongCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (message.isEmpty()) return;

        String commandLabel = message.split(" ")[0].substring(1).toLowerCase();

        // Check if the command exists anywhere in the server command map
        if (commandExists(commandLabel)) {
            // Command found, allow normal processing
            return;
        }

        // Command unknown, send custom message and cancel
        String wrongCmdMsg = plugin.getConfig().getString("Command Messages.Wrong Command", "&cUnknown command.");
        wrongCmdMsg = ChatColor.translateAlternateColorCodes('&', wrongCmdMsg);
        player.sendMessage(wrongCmdMsg);
        event.setCancelled(true);
    }

    private boolean commandExists(String commandLabel) {
        try {
            SimplePluginManager spm = (SimplePluginManager) Bukkit.getPluginManager();
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(spm);

            Command command = commandMap.getCommand(commandLabel);
            return command != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
