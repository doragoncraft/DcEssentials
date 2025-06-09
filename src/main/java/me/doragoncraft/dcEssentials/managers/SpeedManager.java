package me.doragoncraft.dcEssentials.managers;

import org.bukkit.entity.Player;

public class SpeedManager {

    public static final float DEFAULT_FLY_SPEED = 0.1f;
    public static final float DEFAULT_WALK_SPEED = 0.2f;

    /**
     * Sets the player's fly speed safely between 0.0 and 1.0
     */
    public void setFlySpeed(Player player, float speed) {
        float safeSpeed = clamp(speed, 0f, 1f);
        player.setFlySpeed(safeSpeed);
    }

    /**
     * Sets the player's walk speed safely between 0.0 and 1.0
     */
    public void setWalkSpeed(Player player, float speed) {
        float safeSpeed = clamp(speed, 0f, 1f);
        player.setWalkSpeed(safeSpeed);
    }

    /**
     * Resets the player's fly speed to the default value.
     */
    public void resetFlySpeed(Player player) {
        player.setFlySpeed(DEFAULT_FLY_SPEED);
    }

    /**
     * Resets the player's walk speed to the default value.
     */
    public void resetWalkSpeed(Player player) {
        player.setWalkSpeed(DEFAULT_WALK_SPEED);
    }

    /**
     * Clamps a float value between min and max.
     */
    private float clamp(float value, float min, float max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
