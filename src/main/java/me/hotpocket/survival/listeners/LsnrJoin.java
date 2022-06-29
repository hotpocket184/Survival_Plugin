package me.hotpocket.survival.listeners;

import me.hotpocket.survival.utils.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LsnrJoin implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        if(event.getPlayer().hasPlayedBefore()) {
            event.joinMessage(Component.text(Chat.translate("&a+ &b" + event.getPlayer().getName() + " &7has joined the server!")));
        } else {
            event.joinMessage(Component.text(Chat.translate("&a&l+ &b" + event.getPlayer().getName() + " &7has joined the server! &8[&9#" + Bukkit.getOfflinePlayers().length + "&8]")));
        }
    }
}
