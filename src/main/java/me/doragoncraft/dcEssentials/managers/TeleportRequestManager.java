package me.doragoncraft.dcEssentials.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class TeleportRequestManager {

    private final Map<UUID, UUID> pendingRequests = new HashMap<>();
    private final Set<UUID> toggledOff = new HashSet<>();

    public boolean isToggledOff(Player player) {
        return toggledOff.contains(player.getUniqueId());
    }

    public void toggle(Player player) {
        UUID id = player.getUniqueId();
        if (toggledOff.contains(id)) {
            toggledOff.remove(id);
        } else {
            toggledOff.add(id);
        }
    }

    public void addRequest(Player from, Player to) {
        pendingRequests.put(to.getUniqueId(), from.getUniqueId());
    }

    public boolean hasRequest(Player to) {
        return pendingRequests.containsKey(to.getUniqueId());
    }

    public Player getRequester(Player to) {
        UUID requesterUUID = pendingRequests.get(to.getUniqueId());
        return requesterUUID == null ? null : Bukkit.getPlayer(requesterUUID);
    }

    public void removeRequest(Player to) {
        pendingRequests.remove(to.getUniqueId());
    }
}
