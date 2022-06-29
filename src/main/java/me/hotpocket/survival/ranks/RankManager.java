package me.hotpocket.survival.ranks;

import me.hotpocket.survival.Survival;
import me.hotpocket.survival.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RankManager {

    private static YamlConfiguration rankConfig = YamlConfiguration.loadConfiguration(new File(Survival.getInstance().getDataFolder(), "ranks.yml"));

    public static void init() {
        File rankFile = new File(Survival.getInstance().getDataFolder(), "ranks.yml");
        try {
            rankFile.createNewFile();
        } catch(IOException err) {
            Bukkit.getLogger().warning("Unable to create the file 'ranks.yml'!");
        }
    }

    public static void setRank(OfflinePlayer player, String rank) {
        if(RankUtils.exists(rank.toUpperCase().replaceAll("-", "_"))) {
            if (!rankConfig.contains(player.getUniqueId().toString())) {
                rankConfig.addDefault(player.getUniqueId().toString(), rank);
            }
            rankConfig.set(player.getUniqueId().toString(), rank);
            try {
                rankConfig.save(new File(Survival.getInstance().getDataFolder(), File.separator + "ranks.yml"));
            } catch (IOException err) {
                Bukkit.getLogger().warning("Failed to save the file 'ranks.yml'!");
            }
        }
    }

    public static String getRank(OfflinePlayer player) {
        if(rankConfig.contains(player.getUniqueId().toString())) {
            return rankConfig.get(player.getUniqueId().toString()).toString();
        }
        return "default";
    }

    public static int getPermissionLevel(OfflinePlayer player) {
        switch(getRank(player)) {
            case("owner"):
                return 14;
            case("co-owner"):
                return 13;
            case("admin"):
                return 12;
            case("head-dev"):
                return 11;
            case("dev"):
                return 10;
            case("trial-dev"):
                return 9;
            case("sr-mod"):
                return 8;
            case("mod"):
                return 7;
            case("jr-mod"):
                return 6;
            case("trial-mod"):
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

    public enum Rank {
        OWNER,
        CO_OWNER,
        ADMIN,
        HEAD_DEV,
        DEV,
        TRIAL_DEV,
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
