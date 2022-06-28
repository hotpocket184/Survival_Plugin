package me.hotpocket.survival;

import me.hotpocket.survival.commands.CMDGameMode;
import me.hotpocket.survival.commands.CMDReload;
import me.hotpocket.survival.commands.CMDRules;
import me.hotpocket.survival.listeners.LsnrRightClick;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public final class Survival extends JavaPlugin {

    private static Survival instance;
    private SimpleCommandMap commandMap;

    @Override
    public void onEnable() {
        instance = this;

        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()){
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

            Bukkit.getLogger().info("&aSuccessfully registered commands.");
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().info("&cAn error occurred when registering commands.");
        }

        Bukkit.getPluginManager().registerEvents(new LsnrRightClick(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Survival getInstance() {
        return instance;
    }
}
