package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.heal")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.heal")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                Player player = (Player) sender;
                if(args.length == 0) {
                    if(!(sender instanceof Player)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.sender-not-player")));
                        return false;
                    }
                    double HealthLevel = player.getMaxHealth();
                    player.setHealth(HealthLevel);
                    player.setFoodLevel(20);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.heal-command")));
                }
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return false;
                    }
                    double HealthLevelTarget = target.getMaxHealth();
                    target.setHealth(HealthLevelTarget);
                    target.setFoodLevel(20);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.heal-command-target").replace("{player_name}", player.getName())));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.heal-command-target-me")));
                }
            }
            return true;
        }
    }
}
