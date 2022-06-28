package me.hotpocket.survival.commands;

import me.hotpocket.survival.Survival;
import me.hotpocket.survival.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDRules extends BukkitCommand {

    public CMDRules(String name) {
        super(name);
        this.description = "Send the server's rules to the player.";
        this.usageMessage = "/rules";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot use this command.");
            return true;
        }

        Player player = (Player) sender;
        for(String rules : Survival.getInstance().getConfig().getStringList("rules")) {
            Chat.sendMessage(player, rules);
        }
        return true;
    }
}
