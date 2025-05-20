package com.blisssmp1;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

// Listens for player interactions to trigger the Fire Gem ability.
public class FireGemListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        //prevents action from triggering when interacting with doors or chests
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        Player player = event.getPlayer();
//        ItemStack hand = player.getItemInHand();
        ItemStack hand = player.getInventory().getItemInOffHand();
//        int distance = 6;

        if (hand.hasItemMeta() && (ChatColor.DARK_RED.toString() + "Fire Gem").equals(hand.getItemMeta().getDisplayName())) {
            player.launchProjectile(org.bukkit.entity.Fireball.class);
            player.sendMessage(ChatColor.RED + "[Fire Gem]" + ChatColor.YELLOW + " Fireball launched!");
        }


//        if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Fire Gem")) {
//            RayTraceResult result = player.rayTraceBlocks(distance);
//
//            if (result != null && result.getHitBlock() != null && result.getHitBlock().getType().isSolid()) {
//                player.getWorld().createExplosion(result.getHitBlock().getLocation(), 5F, true);
//            }
//
//            else
//                player.sendMessage(ChatColor.RED + "[laser]" + ChatColor.YELLOW + " Target is to far");
//
//        }
    }
}
