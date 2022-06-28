package me.hotpocket.survival.commands;

import me.hotpocket.survival.Survival;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDReload extends BukkitCommand {

    public CMDReload(String name) {
        super(name);

        this.description = "Reloads the server's config.yml.";
        this.usageMessage = "/reload-config";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        Survival.getInstance().reloadConfig();
        Survival.getInstance().saveDefaultConfig();
        if(sender instanceof Player player) {
            Chat.sendMessage(player, "&a&lSUCCESS &7The configuration files have been reloaded!");
        }
        return true;
    }
}
