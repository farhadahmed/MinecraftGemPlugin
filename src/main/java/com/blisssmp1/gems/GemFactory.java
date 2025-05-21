package com.blisssmp1.gems;

import com.blisssmp1.BlissSmp1;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.Optional;

/*
This class will work like a gem crafting machine.
This is a single place to create gem items based on their GemType and
a way to tag those items invisibly using metadata (so we can detect them later)
and a utility to identify a gem's type when the player interacts with it.
 */

public class GemFactory {

    // Creates a custom copper ingot that represents a gem
    // with visual flair and hidden metadata.
    public static ItemStack createGem(GemType type) {
        ItemStack gem = new ItemStack(Material.COPPER_INGOT);
        ItemMeta meta = gem.getItemMeta();

        // Sets the display name that the player will see.
        meta.setDisplayName(type.getDisplayName());

        // Adds lore -- gray text below the item name.
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "A magical gem of " + type.getId()));

        // Adds fake enchantment so the item glows but hides it from the tooltip.
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);

        // Tag the item internally with "gem-type": "fire" or "speed", etc.
        // This makes it machine-readable, unlike display names or lore,
        // which players can tamper with.
        NamespacedKey key = new NamespacedKey(BlissSmp1.getInstance(), "gem-type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, type.getId());

        // Applies the changes and returns the finished ItemStack
        gem.setItemMeta(meta);
        return gem;
    }

    // Given a held item, return the GemType if it is a recognized gem.
    public static Optional<GemType> getGemType(ItemStack item) {

        // If it's null or not a custom item, it's not a gem.
        if (item == null || !item.hasItemMeta()) return Optional.empty();
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(BlissSmp1.getInstance(), "gem-type");

        // Reads the hidden gem-type field (e.g. "fire") and maps it back to GemType.FIRE.
        String id = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return GemType.fromId(id);
    }
}
