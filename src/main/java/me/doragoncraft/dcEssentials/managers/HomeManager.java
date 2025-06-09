package me.doragoncraft.dcEssentials.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class HomeManager {

    private final File homesFile;
    private final FileConfiguration homesConfig;

    public HomeManager(JavaPlugin plugin) {
        homesFile = new File(plugin.getDataFolder(), "homes.yml");
        if (!homesFile.exists()) {
            try {
                homesFile.getParentFile().mkdirs();
                homesFile.createNewFile();
                Bukkit.getLogger().info("[DCEasyCmdsPrem] Created homes.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        homesConfig = YamlConfiguration.loadConfiguration(homesFile);
    }

    public void setHome(Player player, String name, Location loc) {
        String path = player.getUniqueId() + "." + name;
        homesConfig.set(path + ".world", loc.getWorld().getName());
        homesConfig.set(path + ".x", loc.getX());
        homesConfig.set(path + ".y", loc.getY());
        homesConfig.set(path + ".z", loc.getZ());
        homesConfig.set(path + ".yaw", loc.getYaw());
        homesConfig.set(path + ".pitch", loc.getPitch());
        save();
    }

    public Location getHome(Player player, String name) {
        String path = player.getUniqueId() + "." + name;
        if (!homesConfig.contains(path)) return null;

        World world = Bukkit.getWorld(homesConfig.getString(path + ".world"));
        if (world == null) return null;

        double x = homesConfig.getDouble(path + ".x");
        double y = homesConfig.getDouble(path + ".y");
        double z = homesConfig.getDouble(path + ".z");
        float yaw = (float) homesConfig.getDouble(path + ".yaw");
        float pitch = (float) homesConfig.getDouble(path + ".pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public void deleteHome(Player player, String name) {
        String path = player.getUniqueId() + "." + name;
        homesConfig.set(path, null);
        save();
    }

    public Set<String> getPlayerHomes(UUID uuid) {
        return homesConfig.getConfigurationSection(uuid.toString()) != null
                ? homesConfig.getConfigurationSection(uuid.toString()).getKeys(false)
                : null;
    }

    private void save() {
        try {
            homesConfig.save(homesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
