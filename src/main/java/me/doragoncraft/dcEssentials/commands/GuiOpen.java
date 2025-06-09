package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.GUIcmd;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiOpen implements CommandExecutor {

    private final Dcessentails plugin;

    public GuiOpen(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUti.format("&cOnly players can use this command."));
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            if (player.hasPermission("decp.gui")) {
                player.sendMessage(ChatUti.format("&aOpening Edit GUI!"));
                GUIcmd gui = new GUIcmd(this.plugin);
                gui.openGUI(player);
            } else {
                player.sendMessage(ChatUti.format("&cSorry, you don't have permission to use that."));
            }
        } else {
            player.sendMessage(ChatUti.format("&cIncorrect usage. This command takes no arguments."));
        }

        return true;
    }
}
