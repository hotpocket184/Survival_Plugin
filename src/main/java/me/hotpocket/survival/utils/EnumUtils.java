package me.hotpocket.survival.utils;

import me.hotpocket.survival.ranks.RankManager;

public class EnumUtils {

    public static boolean rankExists(String name) {
        for(RankManager.Rank rank : RankManager.Rank.values()) {
            if(rank.name().equals(name.replaceAll("-", "_").toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
