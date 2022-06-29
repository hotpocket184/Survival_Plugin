package me.hotpocket.survival.listeners;

import me.hotpocket.survival.ranks.RankManager;
import me.hotpocket.survival.utils.Chat;
import me.hotpocket.survival.utils.EnumUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class LsnrChat implements Listener {

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        String rank = RankManager.getRank(event.getPlayer()).toUpperCase();
        String message = "";
        if(RankManager.getRankColor(RankManager.Rank.valueOf(rank.replaceAll("-", "_"))) == "&7") {
            message = " &8› &7" + event.getMessage();
        } else {
            message = " &7› &f" + event.getMessage();
        }
        if(EnumUtils.rankExists(rank.replaceAll("-", "_"))) {
            event.setFormat(Chat.translate(RankManager.getRankColor(RankManager.Rank.valueOf(rank.replaceAll("-", "_"))) + "[" + rank.replace("-PLUS", "+") + "] " + event.getPlayer().getName() + message));
        }
    }
}
