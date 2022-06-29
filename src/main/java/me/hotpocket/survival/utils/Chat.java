package me.hotpocket.survival.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat {

    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendMessage(CommandSender recipient, String message) {
        recipient.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendActionBar(Player player, String message) {
        player.sendActionBar(Component.text(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
