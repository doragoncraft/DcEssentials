package me.doragoncraft.dcEssentials.managers;

import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GodManager {
    private final Set<UUID> godPlayers = new HashSet<>();

    public boolean toggleGod(Player player) {
        UUID uuid = player.getUniqueId();
        if (godPlayers.contains(uuid)) {
            godPlayers.remove(uuid);
            return false; // God mode disabled
        } else {
            godPlayers.add(uuid);
            return true; // God mode enabled
        }
    }

    public void setGod(Player player, boolean enabled) {
        if (enabled) godPlayers.add(player.getUniqueId());
        else godPlayers.remove(player.getUniqueId());
    }

    public boolean isGod(Player player) {
        return godPlayers.contains(player.getUniqueId());
    }
}
