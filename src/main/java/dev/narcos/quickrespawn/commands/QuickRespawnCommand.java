package dev.narcos.quickrespawn.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

@CommandAlias("QuickRespawn|quickrespawn|qr")
public class QuickRespawnCommand extends BaseCommand {

    @Dependency
    private Plugin plugin;

    @Subcommand("reload")
    @CommandPermission("quickrespawn.reload")
    @CommandAlias("qrr")
    @Description("Reloads QuickRespawn's config.")
    public void onReloadCommand(CommandSender sender) {
        this.plugin.reloadConfig();
        this.plugin.getLogger().info("Config reloaded.");
        sender.sendMessage("[QuickRespawn] Config reloaded.");
    }

}
