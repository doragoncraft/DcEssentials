package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Listeners.CmdSpyListener;
import me.doragoncraft.dcEssentials.uti.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpyCommand implements CommandExecutor {

    private final CmdSpyListener listener;

    public CmdSpyCommand(CmdSpyListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("dcprem.cmdspy")) {
            player.sendMessage(ChatColor.DARK_RED + "[CmdSpy] " + Lang.NO_PERM.get());
            return true;
        }

        boolean enabled = listener.toggleSpy(player);

        if (enabled) {
            player.sendMessage(ChatColor.DARK_RED + "[CmdSpy] " + Lang.CmdspyEnabled.get());
        } else {
            player.sendMessage(ChatColor.DARK_RED + "[CmdSpy] " + Lang.CmdspyDisabled.get());
        }

        return true;
    }
}
