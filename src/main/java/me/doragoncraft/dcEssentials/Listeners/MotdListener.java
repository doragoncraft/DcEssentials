package me.doragoncraft.dcEssentials.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public class MotdListener implements Listener, CommandExecutor {

    private final JavaPlugin plugin;
    private FileConfiguration config;
    private final Random random = new Random();

    public MotdListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();

        // Register the command executor for /motdreload
        plugin.getCommand("motdreload").setExecutor(this);
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {
        List<String> motds = config.getStringList("motds");

        if (motds.isEmpty()) {
            event.setMotd(ChatColor.translateAlternateColorCodes('&', "&aWelcome to the server!"));
            return;
        }

        String rawMotd = motds.get(random.nextInt(motds.size()));
        Bukkit.getLogger().info("[dcprem] Raw MOTD: " + rawMotd);

        // Replace placeholders except %rainbow%
        String motd = rawMotd
                .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%max%", String.valueOf(event.getMaxPlayers()))
                .replace("%motd_color%", getRandomColorCode());

        // Now handle %rainbow% plus the text after it on the same line
        if (motd.contains("%rainbow%")) {
            int index = motd.indexOf("%rainbow%");
            String before = motd.substring(0, index);
            String after = motd.substring(index + "%rainbow%".length());

            // Apply rainbow effect ONLY to the text after %rainbow%
            String rainbowText = applyRainbowEffect(after);

            // Rebuild motd replacing the whole %rainbow% + text after it with rainbow text only
            motd = before + rainbowText;
        }

        // Replace literal "\n" with newlines
        motd = motd.replace("\\n", "\n");

        // Translate color codes
        String finalMotd = ChatColor.translateAlternateColorCodes('&', motd);

        Bukkit.getLogger().info("[dcprem] Final MOTD: " + finalMotd.replace("\n", "\\n"));

        event.setMotd(finalMotd);
    }



    private String replacePlaceholders(String motd, ServerListPingEvent event) {
        int online = Bukkit.getOnlinePlayers().size();
        int maxPlayers = event.getMaxPlayers();
        String randomColor = getRandomColorCode();

        // Remove %rainbow% before applying rainbow effect to avoid recursion
        String motdForRainbow = motd.replace("%rainbow%", "");
        String rainbowMotd = applyRainbowEffect(motdForRainbow);

        return motd
                .replace("%online%", String.valueOf(online))
                .replace("%max%", String.valueOf(maxPlayers))
                .replace("%motd_color%", randomColor)
                .replace("%rainbow%", rainbowMotd);
    }

    private String getRandomColorCode() {
        ChatColor[] colors = {
                ChatColor.AQUA, ChatColor.RED, ChatColor.BLUE,
                ChatColor.GREEN, ChatColor.YELLOW, ChatColor.LIGHT_PURPLE,
                ChatColor.GOLD, ChatColor.DARK_AQUA, ChatColor.DARK_GREEN
        };
        return colors[random.nextInt(colors.length)].toString();
    }

    private String applyRainbowEffect(String text) {
        ChatColor[] colors = {
                ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW,
                ChatColor.GREEN, ChatColor.AQUA, ChatColor.BLUE,
                ChatColor.LIGHT_PURPLE
        };

        StringBuilder rainbowText = new StringBuilder();
        int colorIndex = 0;

        for (char c : text.toCharArray()) {
            if (c != ' ') {
                rainbowText.append(colors[colorIndex % colors.length]).append(c);
                colorIndex++;
            } else {
                rainbowText.append(c);
            }
        }
        return rainbowText.toString();
    }

    // CommandExecutor method to handle /motdreload
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("motdreload")) {
            return false;
        }

        if (!sender.hasPermission("dcprem.motdreload")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to reload MOTDs.");
            return true;
        }

        reload();
        sender.sendMessage(ChatColor.GREEN + "MOTDs reloaded from config.");
        return true;
    }
}
