package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Craft
        implements CommandExecutor
{
    public Craft(Dcessentails plugin) {}

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {

            sender.sendMessage(Lang.CONSOLE.get());

            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("craft")) {

            if (!sender.hasPermission("decp.craft")) {

                p.sendMessage(Lang.NO_PERM.get());
                return true;
            }
            p.openWorkbench(null, true);
            return false;
        }
        return false;
    }
}
