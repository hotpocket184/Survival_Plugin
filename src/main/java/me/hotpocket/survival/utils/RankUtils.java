package me.hotpocket.survival.utils;

import me.hotpocket.survival.ranks.RankManager;

public class RankUtils {

    public static boolean exists(String name) {
        for(RankManager.Rank rank : RankManager.Rank.values()) {
            if(rank.name().equals(name.replaceAll("-", "_").toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
