package me.doragoncraft.dcEssentials.managers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeManager {

    public void setGamemode(Player player, GameMode mode) {
        player.setGameMode(mode);

        // Auto allow/disallow flight for specific modes
        if (mode == GameMode.CREATIVE || mode == GameMode.SPECTATOR) {
            player.setAllowFlight(true);
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
        }
    }
}
