package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.managers.GodManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;

public class GodModeListener implements Listener {

    private final GodManager godManager;

    public GodModeListener(Dcessentails plugin) {
        this.godManager = plugin.getGodManager();
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if (godManager.isGod(player)) {
            event.setCancelled(true);  // Cancel all damage
        }
    }
}
