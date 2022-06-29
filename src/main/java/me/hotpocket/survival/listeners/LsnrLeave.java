package me.hotpocket.survival.listeners;

import me.hotpocket.survival.utils.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LsnrLeave implements Listener {

    @EventHandler
    private void onLeave(PlayerQuitEvent event) {
        event.quitMessage(Component.text(Chat.translate("&c&l- &f" + event.getPlayer().getName() + " &7has left.")));
    }
}
