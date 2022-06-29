package me.hotpocket.survival;

import me.hotpocket.survival.commands.*;
import me.hotpocket.survival.listeners.LsnrJoin;
import me.hotpocket.survival.listeners.LsnrLeave;
import me.hotpocket.survival.listeners.LsnrRightClick;
import me.hotpocket.survival.ranks.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public final class Survival extends JavaPlugin {

    private static Survival instance;
    private SimpleCommandMap commandMap;

    @Override
    public void onEnable() {
        instance = this;

        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        } else {
            reloadConfig();
            this.saveDefaultConfig();
        }

        this.getCommand("gma").setExecutor(new CMDGameMode());
        this.getCommand("gmc").setExecutor(new CMDGameMode());
        this.getCommand("gms").setExecutor(new CMDGameMode());
        this.getCommand("gmsp").setExecutor(new CMDGameMode());

        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);

            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("Survival", new CMDRules("rules"));
            commandMap.register("Survival", new CMDReload("reload-config"));
            commandMap.register("Survival", new CMDRank("rank"));
            commandMap.register("Survival", new CMDClearChat("clearchat"));

            Bukkit.getLogger().info("&aSuccessfully registered commands.");
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().info("&cAn error occurred when registering commands.");
        }

        Bukkit.getPluginManager().registerEvents(new LsnrRightClick(), this);
        Bukkit.getPluginManager().registerEvents(new LsnrJoin(), this);
        Bukkit.getPluginManager().registerEvents(new LsnrLeave(), this);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(getInstance(), "enchanted_apple"), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        recipe.shape("***", "*o*", "***");
        recipe.setIngredient('*', Material.GOLD_BLOCK);
        recipe.setIngredient('o', Material.APPLE);
        Bukkit.addRecipe(recipe);

        RankManager.init();
        RankManager.save();
    }

    @Override
    public void onDisable() {
        reloadConfig();
        this.saveDefaultConfig();

        Bukkit.removeRecipe(new NamespacedKey(getInstance(), "enchanted_apple"));

        RankManager.save();
    }

    public static Survival getInstance() {
        return instance;
    }
}
