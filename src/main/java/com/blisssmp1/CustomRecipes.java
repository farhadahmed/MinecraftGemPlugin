package com.blisssmp1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

// Adds custom crafting recipes to the game.
public class CustomRecipes {

    public static void register() {

        ItemStack upgradeKit = new ItemStack(Material.PAPER);
        ItemMeta upgradeKitMeta = upgradeKit.getItemMeta();
        upgradeKitMeta.setDisplayName(ChatColor.DARK_GRAY + "Repair Kit");
        upgradeKitMeta.addEnchant(Enchantment.MENDING, 1, true);
        upgradeKitMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        upgradeKit.setItemMeta(upgradeKitMeta);

        ShapedRecipe recipe0 = new ShapedRecipe(new NamespacedKey(BlissSmp1.getInstance(), "UpgradeKitRecipe"), upgradeKit);
        recipe0.shape(" N ", "NWN", " N ");
        recipe0.setIngredient('N', Material.NETHERITE_INGOT);
        recipe0.setIngredient('W', Material.WITHER_SKELETON_SKULL);

        Bukkit.addRecipe(recipe0);
    }
}
