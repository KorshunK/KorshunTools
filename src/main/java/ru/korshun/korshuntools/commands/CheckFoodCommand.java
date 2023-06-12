package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.korshun.korshuntools.KorshunTools;

public class CheckFoodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.checkfood")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.checkfood")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /checkfood <Игрок>");
                    return false;
                }
                if (args.length == 1) {
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    double TargetFoodLevel = target.getFoodLevel();
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return true;
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.target-food-level").replace("{food_level}", String.valueOf(TargetFoodLevel))));
                    return true;
                }
            }
            return true;
        }
    }
}
