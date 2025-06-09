package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.FlyManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeChangeListener implements Listener {

    private final Dcessentails plugin;
    private final FlyManager flyManager;

    public GamemodeChangeListener(Dcessentails plugin, FlyManager flyManager) {
        this.plugin = plugin;
        this.flyManager = flyManager;
    }

    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        GameMode newMode = event.getNewGameMode();

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            if (newMode == GameMode.CREATIVE || newMode == GameMode.SPECTATOR) {
                player.setAllowFlight(true);
            } else {
                if (flyManager.isFlying(player)) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                } else {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                }
            }
        }, 1L);
    }
}
