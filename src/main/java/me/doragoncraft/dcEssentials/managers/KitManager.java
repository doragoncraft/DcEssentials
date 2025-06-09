package me.doragoncraft.dcEssentials.managers;

import me.doragoncraft.dcEssentials.uti.Kit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class KitManager {
    private final Map<String, Kit> kits = new HashMap<>();
    private final Map<UUID, Map<String, Long>> kitCooldowns = new HashMap<>();

    public void createKit(String name, List<ItemStack> items, long delayMillis) {
        kits.put(name.toLowerCase(), new Kit(name, items, delayMillis));
    }

    public Kit getKit(String name) {
        return kits.get(name.toLowerCase());
    }

    public void deleteKit(String name) {
        kits.remove(name.toLowerCase());
    }

    public Collection<Kit> getAllKits() {
        return kits.values();
    }

    public boolean canClaim(Player player, Kit kit) {
        Map<String, Long> playerCooldowns = kitCooldowns.getOrDefault(player.getUniqueId(), new HashMap<>());
        long nextAllowedTime = playerCooldowns.getOrDefault(kit.getName().toLowerCase(), 0L);
        return System.currentTimeMillis() >= nextAllowedTime;
    }

    public long getRemainingCooldown(Player player, Kit kit) {
        Map<String, Long> playerCooldowns = kitCooldowns.getOrDefault(player.getUniqueId(), new HashMap<>());
        long nextAllowedTime = playerCooldowns.getOrDefault(kit.getName().toLowerCase(), 0L);
        return Math.max(0, nextAllowedTime - System.currentTimeMillis());
    }

    public void setCooldown(Player player, Kit kit) {
        kitCooldowns.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                .put(kit.getName().toLowerCase(), System.currentTimeMillis() + kit.getDelayMillis());
    }
}
