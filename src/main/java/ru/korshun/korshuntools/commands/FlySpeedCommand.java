package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class FlySpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.flyspeed")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.flyspeed")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Укажите скорость!");
                    return false;
                }
                if (args.length == 1) {
                    Float DoubleSpeed = Float.parseFloat(args[0]);
                    if(DoubleSpeed < 0 || DoubleSpeed > 10) {
                        sender.sendMessage(ChatColor.RED + "Укажите правильную скорость!");
                        return false;
                    }
                    if (DoubleSpeed == 1) {
                        player.setFlySpeed(0.1F);
                    } else if (DoubleSpeed == 2) {
                        player.setFlySpeed(0.2F);
                    } else if (DoubleSpeed == 3) {
                        player.setFlySpeed(0.3F);
                    } else if (DoubleSpeed == 4) {
                        player.setFlySpeed(0.4F);
                    } else if (DoubleSpeed == 5) {
                        player.setFlySpeed(0.5F);
                    } else if (DoubleSpeed == 6) {
                        player.setFlySpeed(0.6F);
                    } else if (DoubleSpeed== 7) {
                        player.setFlySpeed(0.7F);
                    } else if (DoubleSpeed == 8) {
                        player.setFlySpeed(0.8F);
                    } else if (DoubleSpeed == 9) {
                        player.setFlySpeed(0.9F);
                    } else if (DoubleSpeed == 10) {
                        player.setFlySpeed(1F);
                    }
                    sender.sendMessage(ChatColor.GREEN + "Вы установили себе скорость полета " + DoubleSpeed);
                    return true;
                }
            }
            if (!sender.hasPermission("korshuntools.flyspeed.others")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (args.length == 2) {
                    String targetname = args[1];
                    Player target = Bukkit.getPlayer(targetname);
                    Float DoubleSpeedTarget = Float.parseFloat(args[0]);
                    if(target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return false;
                    }
                    if (DoubleSpeedTarget == 1) {
                        target.setFlySpeed(0.1F);
                    } else if (DoubleSpeedTarget == 2) {
                        target.setFlySpeed(0.2F);
                    } else if (DoubleSpeedTarget == 3) {
                        target.setFlySpeed(0.3F);
                    } else if (DoubleSpeedTarget == 4) {
                        target.setFlySpeed(0.4F);
                    } else if (DoubleSpeedTarget == 5) {
                        target.setFlySpeed(0.5F);
                    } else if (DoubleSpeedTarget == 6) {
                        target.setFlySpeed(0.6F);
                    } else if (DoubleSpeedTarget == 7) {
                        target.setFlySpeed(0.7F);
                    } else if (DoubleSpeedTarget == 8) {
                        target.setFlySpeed(0.8F);
                    } else if (DoubleSpeedTarget == 9) {
                        target.setFlySpeed(0.9F);
                    } else if (DoubleSpeedTarget == 10) {
                        target.setFlySpeed(1F);
                    }
                        target.sendMessage(ChatColor.GREEN + "Вам установили скорость полета на " + DoubleSpeedTarget);
                        player.sendMessage(ChatColor.GREEN + "Вы установили игроку " + target.getName() + " скорость полета на значение " + DoubleSpeedTarget);
                        return true;
                    }
                }
            }
        sender.sendMessage(ChatColor.RED + "Неизвестный аргумент!");
        return false;
    }
}