package me.doragoncraft.dcEssentials.Listeners;

import me.doragoncraft.dcEssentials.Dcessentails;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatAsyncListener implements Listener {

    private final Dcessentails plugin;
    private final String setMsg;
    private final String setGui;

    public ChatAsyncListener(Dcessentails plugin) {
        this.plugin = plugin;
        this.setMsg = ChatUti.format("&7&m----------------------\n&cYou have set the message to&f: ");
        this.setGui = ChatUti.format("&b\n&ftype &8[&b/setgui&8]&f and click \non the &8[&cbarrier&8]&f to exit edit mode!\n\n&7&m----------------------");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        boolean handled = tryHandleMessage(player, event, plugin.getFrPlayer(), "Discord", "Titles.maintitle")
                || tryHandleMessage(player, event, plugin.getSubtitleDiscord(), "Discord", "Titles.subtitle")
                || tryHandleMessage(player, event, plugin.getTitleWebsite(), "Website", "Titles.maintitle")
                || tryHandleMessage(player, event, plugin.getSubTitleWebsite(), "Website", "Titles.subtitle")
                || tryHandleMessage(player, event, plugin.getStore(), "Store", "Titles.maintitle")
                || tryHandleMessage(player, event, plugin.getStoreSub(), "Store", "Titles.subtitle")
                || tryHandleMessage(player, event, plugin.getVote(), "Vote", "Titles.maintitle")
                || tryHandleMessage(player, event, plugin.getVoteSub(), "Vote", "Titles.subtitle");

        if (handled) {
            plugin.saveConfig();
        }
    }

    private boolean tryHandleMessage(Player player, AsyncPlayerChatEvent event,
                                     java.util.Collection<Player> playerList,
                                     String configSectionName, String configPath) {
        if (playerList.contains(player)) {
            event.setCancelled(true);
            ConfigurationSection section = plugin.getConfig().getConfigurationSection(configSectionName);
            if (section != null) {
                section.set(configPath, event.getMessage());
                player.sendMessage(setMsg + event.getMessage() + setGui);
                return true;
            }
        }
        return false;
    }
}
