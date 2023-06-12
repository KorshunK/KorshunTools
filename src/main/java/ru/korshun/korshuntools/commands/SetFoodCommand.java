package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.korshun.korshuntools.KorshunTools;

public class SetFoodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.setfood")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.setfood")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /setfood <Игрок> <Уровень>");
                    return true;
                } else if (args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "Укажите уровень еды!");
                    return true;
                }
                if (args.length == 2) {
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    int FoodLevel = Integer.parseInt(args[1]);
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return true;
                    }
                    target.setFoodLevel(FoodLevel);
                    target.sendMessage(ChatColor.GREEN + "Вам установили уровень еды на значение " + FoodLevel);
                    return true;
                }
            }
            return true;
        }
    }
}