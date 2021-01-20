package dev.narcos.quickrespawn;

import co.aikar.commands.PaperCommandManager;
import dev.narcos.quickrespawn.commands.QuickRespawnCommand;
import dev.narcos.quickrespawn.events.DeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuickRespawn extends JavaPlugin {

    private static QuickRespawn plugin;
    private static PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("QuickRespawn starting up!");
        saveDefaultConfig();
        initializeCommands();
        initializeEvents();
    }

    @Override
    public void onDisable() {
        getLogger().info("QuickRespawn shutting down!");
    }

    public void initializeEvents() {
        getServer().getPluginManager().registerEvents(new DeathEvent(plugin), plugin);
    }

    public void initializeCommands() {
        commandManager = new PaperCommandManager(plugin);
        commandManager.registerCommand(new QuickRespawnCommand());
    }

    public static QuickRespawn getPlugin() {
        return plugin;
    }

    public static PaperCommandManager getCommandManager() {
        return commandManager;
    }
}
