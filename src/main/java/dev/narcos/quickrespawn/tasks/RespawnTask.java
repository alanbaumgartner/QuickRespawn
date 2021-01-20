package dev.narcos.quickrespawn.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnTask extends BukkitRunnable {
    private final Player player;

    public RespawnTask(Player player) {
        this.player = player;
    }

    public void run() {
        this.player.spigot().respawn();
    }
}