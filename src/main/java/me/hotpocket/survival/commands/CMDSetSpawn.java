package me.hotpocket.survival.commands;

import me.hotpocket.survival.Survival;
import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDSetSpawn extends BukkitCommand {

    public CMDSetSpawn(String name) {
        super(name);
        this.description = "Sets the server spawn to your current location.";
        this.usageMessage = "/setspawn";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if(RankManager.getPermissionLevel(player) > 10 || player.isOp()) {
                RankManager.setSpawn(player.getLocation());
                Chat.sendMessage(player, "&a&lSUCCESS &7You have set the spawn of the server to: &b" + Math.round(player.getLocation().getX()) + ", " + Math.round(player.getLocation().getY()) + ", " + Math.round(player.getLocation().getZ()));
                return true;
            } else {
                Chat.sendMessage(player, "&c&lERROR &7You do not have permission to use this command!");
            }
        }
        return false;
    }
}
