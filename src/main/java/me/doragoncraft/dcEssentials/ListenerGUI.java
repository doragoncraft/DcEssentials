package me.doragoncraft.dcEssentials;

import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class ListenerGUI implements Listener {

    private final Dcessentails plugin;

    public ListenerGUI(Dcessentails plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection cs = config.getConfigurationSection("gui.inventory");

        if (cs == null) return;

        String guiTitle = ChatUti.format(config.getString("gui.title", "Menu"));
        if (!event.getView().getTitle().equals(guiTitle)) return;

        cs.getKeys(false).forEach(item -> {
            int slot = cs.getInt(item + ".slot");
            Material material = Material.getMaterial(cs.getString(item + ".material", "STONE"));

            if (event.getSlot() == slot && event.getCurrentItem().getType() == material) {
                event.setCancelled(true);

                List<String> messages = cs.getStringList(item + ".message-send");
                messages.forEach(line -> player.sendMessage(ChatUti.format(line)));

                if (!player.hasPermission("decp.gui")) {
                    player.sendMessage(ChatUti.format("&cI can't let you do that."));
                    return;
                }

                switch (item) {
                    case "item1":
                        clearPlayerStates(player);
                        plugin.getFrPlayer().add(player);
                        break;

                    case "item2":
                        clearPlayerStates(player);
                        plugin.getSubtitleDiscord().add(player);
                        break;

                    case "item3":
                        clearPlayerStates(player);
                        plugin.getTitleWebsite().add(player);
                        break;

                    case "item4":
                        clearPlayerStates(player);
                        plugin.getSubTitleWebsite().add(player);
                        break;

                    case "item5":
                        clearPlayerStates(player);
                        plugin.getStore().add(player);
                        break;

                    case "item6":
                        clearPlayerStates(player);
                        plugin.getStoreSub().add(player);
                        break;

                    case "item7":
                        clearPlayerStates(player);
                        plugin.getVote().add(player);
                        break;

                    case "item8":
                        clearPlayerStates(player);
                        plugin.getVoteSub().add(player);
                        break;

                    case "barrier":
                        clearPlayerStates(player);
                        break;

                    default:
                        break;
                }

                player.closeInventory();
            }
        });
    }

    private void clearPlayerStates(Player player) {
        plugin.getFrPlayer().remove(player);
        plugin.getSubtitleDiscord().remove(player);
        plugin.getSubTitleWebsite().remove(player);
        plugin.getTitleWebsite().remove(player);
        plugin.getStore().remove(player);
        plugin.getStoreSub().remove(player);
        plugin.getVote().remove(player);
        plugin.getVoteSub().remove(player);
    }
}
