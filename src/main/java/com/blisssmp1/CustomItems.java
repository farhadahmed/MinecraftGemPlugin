package com.blisssmp1;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

// Handles the /firegem command
public class CustomItems implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("only players can use this command");
            return true;
        }

        // When a player types /firegem, gives them a custom copper ingot called Fire Gem
        Player player = (Player) sender;
        ItemStack fireGem = new ItemStack(Material.COPPER_INGOT);
        ItemMeta meta = fireGem.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_RED + "Fire Gem");
        meta.setLore(Arrays.asList(
                "",
                ChatColor.GRAY + "This gem was forged in fire by the ancients"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);

        fireGem.setItemMeta(meta);
        player.getInventory().addItem(fireGem);

        return true;
    }
}
