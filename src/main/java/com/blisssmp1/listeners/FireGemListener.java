package com.blisssmp1.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

// Listens for player interactions to trigger the Fire Gem ability.
public class FireGemListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        // Only trigger right-click in air (not blocks, doors, etc).
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        Player player = event.getPlayer();
        ItemStack offHand = player.getInventory().getItemInOffHand();

        if (offHand == null || offHand.getType() != org.bukkit.Material.COPPER_INGOT) return;

        // Check if it has a display name
        if (!offHand.hasItemMeta() || !offHand.getItemMeta().hasDisplayName()) return;

        String displayName = offHand.getItemMeta().getDisplayName();
        String expected = ChatColor.DARK_RED + "Fire Gem";

        if (displayName.equals(expected)) {
            player.launchProjectile(org.bukkit.entity.Fireball.class);
            player.sendMessage(ChatColor.RED + "[Fire Gem]" + ChatColor.YELLOW + " Fireball launched!");
        }
    }
}
