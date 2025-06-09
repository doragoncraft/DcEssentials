package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CmdSpyListener implements Listener {

    private final Dcessentails plugin;
    private final Set<UUID> spyingPlayers;

    public CmdSpyListener(Dcessentails plugin) {
        this.plugin = plugin;
        this.spyingPlayers = new HashSet<>();
    }

    // Toggle spying state for a player
    public boolean toggleSpy(Player player) {
        UUID uuid = player.getUniqueId();
        if (spyingPlayers.contains(uuid)) {
            spyingPlayers.remove(uuid);
            return false; // now disabled
        } else {
            spyingPlayers.add(uuid);
            return true; // now enabled
        }
    }

    // Check if player is spying
    public boolean isSpying(Player player) {
        return spyingPlayers.contains(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player sender = event.getPlayer();
        String msg = event.getMessage();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (spyingPlayers.contains(p.getUniqueId()) && !p.equals(sender)) {
                p.sendMessage(ChatColor.RED + sender.getName() + ChatColor.DARK_RED + ": " + ChatColor.GRAY + msg);
                plugin.getLogger().info("CmdSpy: sent command from " + sender.getName() + " to " + p.getName());
            }
        }
    }
}
