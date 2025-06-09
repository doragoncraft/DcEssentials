package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishListener implements Listener {

    private final Dcessentails plugin;

    public VanishListener(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joiningPlayer = event.getPlayer();

        // If player cannot see vanished players, hide all vanished players from them
        if (!joiningPlayer.hasPermission("decp.vanish.see")) {
            for (Player vanishedPlayer : plugin.getVanished()) {
                joiningPlayer.hidePlayer(plugin, vanishedPlayer);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player quittingPlayer = event.getPlayer();

        // Safely remove player from vanished list
        plugin.removeVanished(quittingPlayer);
    }
}
