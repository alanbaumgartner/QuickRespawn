package dev.narcos.quickrespawn.events;

import dev.narcos.quickrespawn.tasks.RespawnTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class DeathEvent  implements Listener {

    private final Plugin plugin;

    public DeathEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        String currentWorldName = player.getWorld().getName();

        // permission booleans
        boolean enabledPermissions = plugin.getConfig().getBoolean("enable-permissions");
        boolean keepItems = enabledPermissions ? player.hasPermission("quickrespawn.keepitems") : plugin.getConfig().getBoolean("keep-items");
        boolean keepExperience = enabledPermissions ? player.hasPermission("quickrespawn.keepxp") : plugin.getConfig().getBoolean("keep-experience");
        boolean allowQuickRespawn = !enabledPermissions || player.hasPermission("quickrespawn.allow.*") || player.hasPermission("quickrespawn.allow." + currentWorldName);

        plugin.getLogger().config(String.format("user=%s userId=%s allowed=%b keepItems=%b keepExperience=%b",
                player.getDisplayName(), player.getUniqueId(),
                allowQuickRespawn, keepItems, keepExperience));

        if (keepItems) {
            e.setKeepInventory(true);
            e.getDrops().clear();
        }

        if (keepExperience) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }

        if (allowQuickRespawn) {
            (new RespawnTask(player)).runTaskLater(plugin, plugin.getConfig().getLong("respawn-delay"));
        }
    }

}
