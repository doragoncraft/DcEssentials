package me.doragoncraft.dcEssentials;

import java.util.List;
import me.doragoncraft.dcEssentials.uti.ChatUti;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIcmd {

    private final Dcessentails plugin;

    public GUIcmd(Dcessentails plugin) {
        this.plugin = plugin;
    }

    public void openGUI(Player player) {
        FileConfiguration config = plugin.getConfig();

        // Get size and title from config, with fallback defaults
        int size = config.getInt("gui.size", 27);
        String title = ChatUti.format(config.getString("gui.title", "&cGUI"));

        Inventory inventory = Bukkit.createInventory(null, size, title);

        ConfigurationSection cs = config.getConfigurationSection("gui.inventory");
        if (cs == null) {
            player.sendMessage(ChatUti.format("&cGUI configuration section is missing!"));
            return;
        }

        for (String key : cs.getKeys(false)) {
            int slot = cs.getInt(key + ".slot", -1);
            if (slot < 0 || slot >= size) {
                plugin.getLogger().warning("Invalid slot for GUI item " + key + ": " + slot);
                continue;
            }

            int amount = cs.getInt(key + ".amount", 1);
            String materialName = cs.getString(key + ".material", "STONE");
            Material material = Material.getMaterial(materialName);

            if (material == null) {
                plugin.getLogger().warning("Invalid material for GUI item " + key + ": " + materialName);
                continue;
            }

            // Data value (durability) deprecated in modern MC versions — use with caution
            short data = (short) cs.getInt(key + ".data", 0);

            ItemStack itemStack = new ItemStack(material, amount);
            if (data != 0) {
                try {
                    itemStack.setDurability(data);
                } catch (NoSuchMethodError e) {
                    // ignore or log if using new API
                }
            }

            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                String displayname = ChatUti.format(cs.getString(key + ".item.displayname", materialName));
                List<String> lore = ChatUti.format(cs.getStringList(key + ".item.lore"));
                itemMeta.setDisplayName(displayname);
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
            }

            inventory.setItem(slot, itemStack);
        }

        player.openInventory(inventory);
    }
}
