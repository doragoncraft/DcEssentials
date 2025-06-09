package me.doragoncraft.dcEssentials.listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.FlyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FlyListener implements Listener {

    private final Dcessentails plugin;
    private final FlyManager flyManager;

    public FlyListener(Dcessentails plugin) {
        this.plugin = plugin;
        this.flyManager = plugin.getFlyManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Delay 1 tick to avoid conflicts with spawn
        new BukkitRunnable() {
            @Override
            public void run() {
                if (flyManager.isFlying(player)) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                }
            }
        }.runTaskLater(plugin, 1L);
    }
}
