package com.blisssmp1;

import com.blisssmp1.commands.GiveGemCommand;
import com.blisssmp1.listeners.FireGemListener;
import com.blisssmp1.tasks.FireGemTask;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

// This is the point of entry that sets everything up.
// Controls startup and shutdown behavior of the plugin.
public final class BlissSmp1 extends JavaPlugin implements Listener {

    private BukkitTask task;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new FireGemListener(), this);

//        getCommand("firegem").setExecutor(new CustomItems());
        getCommand("givegem").setExecutor(new GiveGemCommand());

        CustomRecipes.register();

        task = getServer().getScheduler().runTaskTimer(this, FireGemTask.getInstance(), 0, 1);
    }

    @Override
    public void onDisable() {
        if (task != null && !task.isCancelled())
            task.cancel();
    }

    public static BlissSmp1 getInstance() {
        return getPlugin(BlissSmp1.class);
    }
}
