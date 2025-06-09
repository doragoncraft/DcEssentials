package me.doragoncraft.dcEssentials.commands;

import me.doragoncraft.dcEssentials.Dcessentails;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PortableEnchant implements CommandExecutor, Listener {

    private final Dcessentails plugin;
    private final Random random = new Random();

    private static final int ITEM_SLOT = 0;
    private static final int LAPIS_SLOT = 1;
    private static final int[] OPTION_SLOTS = {2, 4, 6};
    private static final int INFO_SLOT = 8;

    private final int[] xpCosts = {10, 20, 30};
    private final int[] lapisCosts = {1, 2, 3};

    public PortableEnchant(Dcessentails plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        Player player = (Player) sender;
        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Portable Enchant Table");

        gui.setItem(INFO_SLOT, createInfoItem());

        player.openInventory(gui);
        return true;
    }

    private ItemStack createInfoItem() {
        ItemStack info = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta meta = info.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Click to Generate Options");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "1. Put an item in slot 0.");
        lore.add(ChatColor.WHITE + "2. Add lapis lazuli in slot 1.");
        lore.add(ChatColor.WHITE + "3. Click this to generate enchant options.");
        meta.setLore(lore);
        info.setItemMeta(meta);
        return info;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(ChatColor.DARK_PURPLE + "Portable Enchant Table")) return;

        int slot = event.getRawSlot();
        if (slot >= event.getInventory().getSize()) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();

        if (slot == INFO_SLOT) {
            event.setCancelled(true);

            ItemStack item = inv.getItem(ITEM_SLOT);
            ItemStack lapis = inv.getItem(LAPIS_SLOT);

            if (item == null || item.getType() == Material.AIR) {
                player.sendMessage(ChatColor.RED + "Put an item to enchant in slot 0.");
                return;
            }
            if (lapis == null || lapis.getType() != Material.LAPIS_LAZULI) {
                player.sendMessage(ChatColor.RED + "Add lapis lazuli to slot 1.");
                return;
            }

            for (int i = 0; i < OPTION_SLOTS.length; i++) {
                inv.setItem(OPTION_SLOTS[i], createEnchantOptionBook(item, i));
            }
            player.sendMessage(ChatColor.GREEN + "Enchant options generated.");
            return;
        }

        if (IntStream.of(OPTION_SLOTS).anyMatch(s -> s == slot)) {
            event.setCancelled(true);

            ItemStack option = event.getCurrentItem();
            if (option == null || option.getType() != Material.ENCHANTED_BOOK) return;

            int optionIndex = IntStream.range(0, OPTION_SLOTS.length)
                    .filter(i -> OPTION_SLOTS[i] == slot)
                    .findFirst().orElse(-1);
            if (optionIndex == -1) return;

            ItemStack item = inv.getItem(ITEM_SLOT);
            ItemStack lapis = inv.getItem(LAPIS_SLOT);

            if (item == null || lapis == null || lapis.getType() != Material.LAPIS_LAZULI) {
                player.sendMessage(ChatColor.RED + "Missing item or lapis.");
                return;
            }

            int xpCost = xpCosts[optionIndex];
            int lapisCost = lapisCosts[optionIndex];

            if (player.getLevel() < xpCost) {
                player.sendMessage(ChatColor.RED + "You need at least " + xpCost + " XP levels.");
                return;
            }

            if (lapis.getAmount() < lapisCost) {
                player.sendMessage(ChatColor.RED + "You need at least " + lapisCost + " lapis lazuli.");
                return;
            }

            List<String> lore = option.getItemMeta().getLore();
            if (lore != null) {
                for (String line : lore) {
                    String clean = ChatColor.stripColor(line);
                    if (clean.contains("Cost")) continue;
                    String[] parts = clean.split(" ");
                    if (parts.length >= 2) {
                        Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(parts[0].toLowerCase()));
                        if (enchant != null) {
                            int level = romanToInt(parts[1]);
                            item.addUnsafeEnchantment(enchant, level);
                        }
                    }
                }
            }

            lapis.setAmount(lapis.getAmount() - lapisCost);
            player.setLevel(player.getLevel() - xpCost);
            player.sendMessage(ChatColor.GREEN + "Enchant applied for " + lapisCost + " lapis & " + xpCost + " XP.");
            inv.setItem(ITEM_SLOT, item);
            player.updateInventory();
        }
    }

    private ItemStack createEnchantOptionBook(ItemStack targetItem, int optionIndex) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = book.getItemMeta();
        int xpCost = xpCosts[optionIndex];
        int lapisCost = lapisCosts[optionIndex];
        meta.setDisplayName(ChatColor.AQUA + "Enchant Option (" + xpCost + " XP, " + lapisCost + " Lapis)");

        List<String> lore = new ArrayList<>();
        int enchants = 1 + random.nextInt(2);
        for (int i = 0; i < enchants; i++) {
            Enchantment enchant = getRandomCompatibleEnchant(targetItem);
            int enchantLevel = 1 + random.nextInt(Math.min(enchant.getMaxLevel(), 5));
            lore.add(ChatColor.GREEN + enchant.getKey().getKey() + " " + toRoman(enchantLevel));
        }
        lore.add(ChatColor.GRAY + "Cost: " + xpCost + " XP, " + lapisCost + " Lapis");
        meta.setLore(lore);
        book.setItemMeta(meta);
        return book;
    }

    private Enchantment getRandomCompatibleEnchant(ItemStack item) {
        List<Enchantment> possible = Arrays.stream(Enchantment.values())
                .filter(e -> e.canEnchantItem(item) && !e.isCursed())
                .collect(Collectors.toList());
        return possible.get(random.nextInt(possible.size()));
    }

    private String toRoman(int number) {
        String[] romans = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return number >= 1 && number < romans.length ? romans[number] : String.valueOf(number);
    }

    private int romanToInt(String roman) {
        switch (roman) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: return 1;
        }
    }
}
