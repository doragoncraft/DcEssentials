package me.doragoncraft.dcEssentials.uti;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ChatUti
{
    public static Inventory askGUI;

    public static String format(String string) { return ChatColor.translateAlternateColorCodes('&', string); }

    public static void survivalItem(Player player) { ItemStack diamondsword = new ItemStack((player.getGameMode() == GameMode.SURVIVAL) ? Material.DIAMOND_SWORD : Material.ANVIL); }



    public static void openAskGUI(Player player) {
        askGUI = Bukkit.createInventory(player, 9, ChatColor.DARK_AQUA + "Lock Detected. Lock Chest?");


        ItemStack yes = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName("&aYes");
        yes.setItemMeta(yes_meta);


        ItemStack diamondsword = new ItemStack((player.getGameMode() == GameMode.SURVIVAL) ? Material.DIAMOND_SWORD : Material.ANVIL);
        ItemStack no = new ItemStack(Material.BARRIER);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName("&4No");
        no.setItemMeta(no_meta);


        ItemStack fill = new ItemStack(Material.GLASS);
        ItemMeta fill_meta = fill.getItemMeta();
        fill_meta.setDisplayName("&r");
        fill.setItemMeta(fill_meta);



        askGUI.setItem(3, diamondsword);
        askGUI.setItem(5, no);

        for (int i = 0; i < 9; i++) {
            if (askGUI.getItem(i) == null) {
                askGUI.setItem(i, fill);
            }
        }
        player.openInventory(askGUI);
    }

    public static List<String> format(List<String> list) { return (List)list.stream().map(ChatUti::format).collect(Collectors.toList()); }
}
