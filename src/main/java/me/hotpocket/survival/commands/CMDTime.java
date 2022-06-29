package me.hotpocket.survival.commands;

import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDTime implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if(RankManager.getPermissionLevel(player) > 10 || player.isOp()) {
                switch(command.getName()) {
                    case("day"):
                        player.getWorld().setTime(1000);
                        break;
                    case("night"):
                        player.getWorld().setTime(13000);
                        break;
                }
                return true;
            } else {
                Chat.sendMessage(player, "&c&lERROR &7You don't have permission to use this command!");
            }
        }
        return false;
    }
}
