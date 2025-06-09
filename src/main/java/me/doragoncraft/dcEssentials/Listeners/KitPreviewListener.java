package me.doragoncraft.dcEssentials.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KitPreviewListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().startsWith(ChatColor.GOLD + "Kit: ")) {
            e.setCancelled(true);
        }
    }
}
