package me.doragoncraft.dcEssentials.managers;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WarpsManager {

    private final Dcessentails plugin;
    private final File warpsFile;
    private final YamlConfiguration warpsConfig;

    // Map warp name -> Location
    private final Map<String, Location> warps = new HashMap<>();

    public WarpsManager(Dcessentails plugin) {
        this.plugin = plugin;
        this.warpsFile = new File(plugin.getDataFolder(), "warps.yml");

        if (!warpsFile.exists()) {
            plugin.saveResource("warps.yml", false);
        }

        this.warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
        loadWarps();
    }

    // Load warps from config into memory
    private void loadWarps() {
        warps.clear();
        for (String key : warpsConfig.getKeys(false)) {
            Location loc = warpsConfig.getLocation(key);
            if (loc != null) {
                warps.put(key.toLowerCase(Locale.ROOT), loc);
            }
        }
    }

    // Save warps from memory back to config file
    public void saveWarps() {
        for (String key : warps.keySet()) {
            warpsConfig.set(key, warps.get(key));
        }
        try {
            warpsConfig.save(warpsFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save warps.yml!");
            e.printStackTrace();
        }
    }

    // Add or update a warp
    public void setWarp(String name, Location location) {
        warps.put(name.toLowerCase(Locale.ROOT), location);
        warpsConfig.set(name.toLowerCase(Locale.ROOT), location);
        saveWarps();
    }

    // Delete a warp, returns true if removed
    public boolean deleteWarp(String name) {
        if (warps.containsKey(name.toLowerCase(Locale.ROOT))) {
            warps.remove(name.toLowerCase(Locale.ROOT));
            warpsConfig.set(name.toLowerCase(Locale.ROOT), null);
            saveWarps();
            return true;
        }
        return false;
    }

    // Get a warp location by name, null if not exists
    public Location getWarp(String name) {
        return warps.get(name.toLowerCase(Locale.ROOT));
    }

    // Get all warp names
    public Set<String> getWarps() {
        return Collections.unmodifiableSet(warps.keySet());
    }

    // Check if a warp exists
    public boolean warpExists(String name) {
        return warps.containsKey(name.toLowerCase(Locale.ROOT));
    }
}
