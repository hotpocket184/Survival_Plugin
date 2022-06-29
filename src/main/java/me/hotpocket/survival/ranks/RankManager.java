package me.hotpocket.survival.ranks;

import me.hotpocket.survival.Survival;
import me.hotpocket.survival.utils.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RankManager {

    private static YamlConfiguration rankConfig = YamlConfiguration.loadConfiguration(new File(Survival.getInstance().getDataFolder(), "ranks.yml"));

    // Don't mind this :P

    public static void setSpawn(Location location) {
        if(!rankConfig.contains("spawn")) {
            rankConfig.addDefault("spawn", location);
        }
        rankConfig.set("spawn", location);
        save();
    }

    public static Location getSpawn() {
        if(rankConfig.contains("spawn")) {
            return rankConfig.getLocation("spawn");
        }
        return null;
    }

    public static void init() {
        File rankFile = new File(Survival.getInstance().getDataFolder(), "ranks.yml");
        if(!rankFile.exists()) {
            try {
                rankFile.createNewFile();
            } catch (IOException err) {
                Bukkit.getLogger().warning("Unable to create the file 'ranks.yml'!");
            }
        }
    }

    public static void setRank(OfflinePlayer player, String rank) {
        if(EnumUtils.rankExists(rank.toUpperCase().replaceAll("-", "_"))) {
            if (!rankConfig.contains(player.getUniqueId().toString())) {
                rankConfig.addDefault(player.getUniqueId().toString(), rank);
            }
            rankConfig.set(player.getUniqueId().toString(), rank);
            try {
                rankConfig.save(new File(Survival.getInstance().getDataFolder(), "ranks.yml"));
            } catch (IOException err) {
                Bukkit.getLogger().warning("Failed to save the file 'ranks.yml'!");
            }
        }
    }

    public static String getRank(OfflinePlayer player) {
        if(!rankConfig.contains(player.getUniqueId().toString())) {
            rankConfig.set(player.getUniqueId().toString(), "default");
        }
        try {
            rankConfig.save(new File(Survival.getInstance().getDataFolder(), "ranks.yml"));
        } catch (IOException err) {
            Bukkit.getLogger().warning("Failed to save the file 'ranks.yml'!");
        }
        return rankConfig.get(player.getUniqueId().toString()).toString();
    }

    public static void save() {
        try {
            rankConfig.save(new File(Survival.getInstance().getDataFolder(), "ranks.yml"));
        } catch (IOException err) {
            Bukkit.getLogger().warning("Failed to save the file 'ranks.yml'!");
        }
    }

    public static int getPermissionLevel(OfflinePlayer player) {
        switch(getRank(player)) {
            case("owner"):
                return 15;
            case("co-owner"):
                return 14;
            case("admin"):
                return 13;
            case("head-dev"):
                return 12;
            case("dev"):
                return 11;
            case("trial-dev"):
                return 10;
            case("sr-mod"):
                return 9;
            case("mod"):
                return 8;
            case("jr-mod"):
                return 7;
            case("trial-mod"):
                return 6;
            case("builder"):
                return 5;
            case("legend"):
                return 4;
            case("elite"):
                return 3;
            case("vip-plus"):
                return 2;
            case("vip"):
                return 1;
            default:
                return 0;
        }
    }

    public static String getRankColor(Rank rank) {
        switch(rank.name()) {
            case("OWNER"):
            case("CO_OWNER"):
                return "&c";
            case("ADMIN"):
            case("VIP_PLUS"):
            case("VIP"):
                return "&a";
            case("HEAD_DEV"):
                return "&5";
            case("DEV"):
            case("TRIAL_DEV"):
                return "&d";
            case("BUILDER"):
                return "&2";
            case("SR_MOD"):
            case("LEGEND"):
                return "&6";
            case("MOD"):
            case("JR_MOD"):
                return "&e";
            case("TRIAL_MOD"):
                return "&9";
            case("ELITE"):
                return "&3";
            default:
                return "&7";
        }
    }

    public enum Rank {
        OWNER,
        CO_OWNER,
        ADMIN,
        HEAD_DEV,
        DEV,
        TRIAL_DEV,
        BUILDER,
        SR_MOD,
        MOD,
        JR_MOD,
        TRIAL_MOD,
        LEGEND,
        ELITE,
        VIP_PLUS,
        VIP,
        DEFAULT
    }
}
