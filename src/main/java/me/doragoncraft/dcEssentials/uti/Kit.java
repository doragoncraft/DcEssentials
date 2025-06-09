package me.doragoncraft.dcEssentials.uti;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Kit {
    private final String name;
    private final List<ItemStack> items;
    private final long delayMillis;

    public Kit(String name, List<ItemStack> items, long delayMillis) {
        this.name = name;
        this.items = items;
        this.delayMillis = delayMillis;
    }

    public String getName() {
        return name;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public long getDelayMillis() {
        return delayMillis;
    }
}
