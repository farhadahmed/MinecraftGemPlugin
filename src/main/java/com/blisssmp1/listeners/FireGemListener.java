package com.blisssmp1.listeners;

import com.blisssmp1.gems.GemFactory;
import com.blisssmp1.gems.GemType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

// Listens for player interactions to trigger the Fire Gem ability.
public class FireGemListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        // Only trigger right-click in air (not blocks, doors, etc).
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        Player player = event.getPlayer();
        ItemStack offHand = player.getInventory().getItemInOffHand();

//        if (offHand == null || offHand.getType() != org.bukkit.Material.COPPER_INGOT) return;
//        if (!offHand.hasItemMeta() || !offHand.getItemMeta().hasDisplayName()) return;

        if (offHand == null || offHand.getType().isAir()) return;

        // Use GemFactory to check if this is a gem, and which one
        Optional<GemType> type = GemFactory.getGemType(offHand);

//        String displayName = offHand.getItemMeta().getDisplayName();
//        String expected = ChatColor.DARK_RED + "Fire Gem";

//        if (displayName.equals(expected)) {
//            player.launchProjectile(org.bukkit.entity.Fireball.class);
//        }

        if (type.isPresent() && type.get() == GemType.FIRE) {
            player.launchProjectile(Fireball.class);
        }
    }
}
