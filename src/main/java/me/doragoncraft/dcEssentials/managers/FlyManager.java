package me.doragoncraft.dcEssentials.managers;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FlyManager {

    private final Set<UUID> flyingPlayers = new HashSet<>();

    public boolean isFlying(Player player) {
        return flyingPlayers.contains(player.getUniqueId());
    }

    public void setFlying(Player player, boolean enabled) {
        player.setAllowFlight(enabled);
        if (!enabled) {
            player.setFlying(false);
            flyingPlayers.remove(player.getUniqueId());
        } else {
            flyingPlayers.add(player.getUniqueId());
        }
    }

    public void toggleFlying(Player player) {
        boolean currentlyFlying = isFlying(player);
        setFlying(player, !currentlyFlying);
    }

    public void removePlayer(Player player) {
        flyingPlayers.remove(player.getUniqueId());
    }
}
