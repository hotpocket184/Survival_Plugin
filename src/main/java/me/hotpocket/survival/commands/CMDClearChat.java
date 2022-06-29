package me.hotpocket.survival.commands;

import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CMDClearChat extends BukkitCommand {

    private List<String> aliases = new ArrayList<>();

    public CMDClearChat(String name) {
        super(name);

        aliases.add("cc");

        this.description = "Clears the server's chat.";
        this.usageMessage = "/clearchat";
        this.setAliases(aliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(RankManager.getPermissionLevel((OfflinePlayer) sender) > 6) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                for(int i = 0; i < 100; i++) {
                    Chat.sendMessage(player, " ");
                }
                Chat.sendMessage(player, "&c[CHAT] &7The chat has been cleared by &b" + sender.getName() + "&7!");
                return true;
            }
        } else {
            Chat.sendMessage((Player) sender, "&c&lERROR &7You do not have permission to use this command!");
        }
        return false;
    }
}
