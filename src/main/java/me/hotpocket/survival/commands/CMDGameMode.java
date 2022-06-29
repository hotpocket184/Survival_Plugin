package me.hotpocket.survival.commands;

import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDGameMode implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = null;
        if (args.length > 0) {
            if (Bukkit.getPlayer(args[0]) != null) {
                player = Bukkit.getPlayer(args[0]);
            } else {
                Chat.sendMessage(sender, "&c&lERROR &7The specified argument is not a player!");
                return false;
            }
        } else {
            if (sender instanceof Player) {
                player = (Player) sender;
            } else {
                Chat.sendMessage(sender, "&c&lERROR &7You need to specify a player to set the gamemode of!");
                return false;
            }
        }
        if(player != null) {
            String mode = null;
            switch (command.getName()) {
                case ("gma"):
                    mode = "adventure";
                    break;
                case ("gmc"):
                    mode = "creative";
                    break;
                case ("gms"):
                    mode = "survival";
                    break;
                case ("gmsp"):
                    mode = "spectator";
                    break;
                default:
                    return false;
            }
            if(RankManager.getPermissionLevel((Player) sender) > 10 || sender.isOp()) {
                player.setGameMode(GameMode.valueOf(mode.toUpperCase()));
                Chat.sendMessage(sender, "&a&lSUCCESS &7You have set &b" + player.getName() + "'s &7gamemode to " + mode + "!");
                return true;
            } else {
                Chat.sendMessage(sender, "&c&lERROR &7You do not have permission to use this command!");
            }
        }
        return false;
    }
}
