package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.FlyManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinListener implements Listener {
    private final Dcessentails plugin;
    private final FlyManager flyManager;
    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "DCEssentails" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " ";

    public OnJoinListener(Dcessentails plugin, FlyManager flyManager) {
        this.plugin = plugin;
        this.flyManager = flyManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // Server uses your plugin notice only sent to me
        if (p.getName().equalsIgnoreCase("DoragonCraft")) {
            p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "This server uses your plugin!");
        }

        // Thank you message for OP players
        if (p.isOp() && plugin.getConfig().getBoolean("Thank-you-message")) {
            TextComponent msg = new TextComponent(ChatColor.AQUA + "Thank you for using: " + ChatColor.WHITE + "DcEasyCmds Premium.\n" + ChatColor.GRAY + "Only server Owners and op's see this message. ^");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(ChatColor.WHITE + "Please consider rating my plugin " + ChatColor.GOLD + "★★★★★" + ChatColor.GRAY + "\nClick Here! To view my other resources.").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/authors/doragoncraft.126499/"));
            p.spigot().sendMessage(msg);
        }

        // Ensure Creative and Spectator players have flight enabled on login
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
                p.setAllowFlight(true);
                // Don't force flying — player can double jump to start flying as normal
            } else {
                // If your /fly manager has a saved state for the player, honor it
                if (plugin.getFlyManager().isFlying(p)) {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                } else {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
            }
        }, 1L); // delay by 1 tick to ensure player is fully initialized
    }
}