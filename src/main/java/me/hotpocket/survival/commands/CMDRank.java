package me.hotpocket.survival.commands;

import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import me.hotpocket.survival.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDRank extends BukkitCommand {

    public CMDRank(String name) {
        super(name);
        this.description = "Manipulates the rank of the specified user.";
        this.usageMessage = "/rank";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (RankManager.getPermissionLevel((Player) sender) > 9) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length > 1) {
                        Bukkit.getOfflinePlayer(args[1]);
                        if (RankUtils.exists(args[2])) {
                            RankManager.setRank(Bukkit.getOfflinePlayer(args[1]), args[2]);
                            Chat.sendMessage((Player) sender, "&a&lSUCCESS &7Set &b" + Bukkit.getOfflinePlayer(args[1]).getName() + "'s &7rank to &6" + args[2].replaceAll("_", "-").toLowerCase() + "&7!");
                            return true;
                        } else {
                            Chat.sendMessage((Player) sender, "&c&lERROR &7There is no rank by the name of: &b" + args[2] + "&7!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("list")) {
                    for (RankManager.Rank rank : RankManager.Rank.values()) {
                        Chat.sendMessage((Player) sender, "&6" + rank.toString().toLowerCase().replaceAll("_", "-"));
                    }
                } else if (args[0].equalsIgnoreCase("get")) {
                    Bukkit.getOfflinePlayer(args[1]);
                    RankManager.getRank(Bukkit.getOfflinePlayer(args[1]));
                    Chat.sendMessage((Player) sender, "&7The rank of &b" + Bukkit.getOfflinePlayer(args[1]).getName() + " &7is currently &6" + RankManager.getRank(Bukkit.getOfflinePlayer(args[1])) + "&7.");
                }
            }
        }
        return false;
    }
}
