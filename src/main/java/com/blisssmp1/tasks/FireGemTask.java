package com.blisssmp1.tasks;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/*
Runs as a repeating background task, tick-by-tick.
Every game tick, it:
- Loops through all online players
- Checks if any are holding the Fire Gem
- Spawns flame particles in a line from their position (visual effect)
- This adds a magical "trail" effect when the gem is held — purely visual.
*/

public class FireGemTask implements Runnable {

    private static final FireGemTask instance = new FireGemTask();

    private FireGemTask() {
    }

    @Override
    public void run() {
        int length = 3;
        double particleDistance = 0.5;

        for (Player online : Bukkit.getOnlinePlayers()) {
            ItemStack hand = online.getItemInHand();

            if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Fire Gem")) {
                Location location = online.getLocation().add(0, 1, 0);
                for (double waypoint = 1; waypoint < length; waypoint += particleDistance) {
                    Vector vector = location.getDirection().multiply(waypoint);
                    location.add(vector);

                    if (location.getBlock().getType() != Material.AIR)
                        break;

                    location.getWorld().spawnParticle(Particle.FLAME, location, 1, new Particle.DustOptions(Color.ORANGE, 0.75F));
                }


            }

        }
    }

    public static FireGemTask getInstance() {
        return instance;
    }
}
