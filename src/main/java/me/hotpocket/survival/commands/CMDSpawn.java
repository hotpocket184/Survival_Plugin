package me.hotpocket.survival.commands;

import me.hotpocket.survival.Survival;
import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CMDSpawn extends BukkitCommand {

    public CMDSpawn(String name) {
        super(name);
        this.description = "Teleports you to the spawn of the server.";
        this.usageMessage = "/spawn";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if(RankManager.getSpawn() != null) {
                player.teleport(Objects.requireNonNull(RankManager.getSpawn()));
                Chat.sendMessage(player, "&a&lSUCCESS &7You have been teleported to the server spawn!");
                return true;
            } else {
                Chat.sendMessage(player, "&c&lERROR &7The spawn of the server has not yet been set!");
            }
        }
        return false;
    }
}
