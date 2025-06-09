package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockCommands implements Listener {

    private final Dcessentails plugin;

    public BlockCommands(Dcessentails plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection cs = plugin.getConfig().getConfigurationSection("Command Block");

        if (cs.getBoolean("Enabled") && !player.isOp()) {
            String message = event.getMessage().toLowerCase();

            for (String cmd : cs.getStringList("Commands")) {
                if (message.startsWith(cmd.toLowerCase())) {
                    player.sendMessage(ChatUti.format(cs.getString("Message")));
                    event.setCancelled(true);
                    break; // Stop checking after first match
                }
            }
        }
    }
}
