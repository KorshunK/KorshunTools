package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class SpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.speed")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.speed")) {
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
                    if(DoubleSpeed == 0.5) {
                        player.setWalkSpeed(0.1F);
                    }
                    else if (DoubleSpeed == 1) {
                        player.setWalkSpeed(0.2F);
                    } else if (DoubleSpeed == 2) {
                        player.setWalkSpeed(0.3F);
                    } else if (DoubleSpeed == 3) {
                        player.setWalkSpeed(0.4F);
                    } else if (DoubleSpeed == 4) {
                        player.setWalkSpeed(0.5F);
                    } else if (DoubleSpeed == 5) {
                        player.setWalkSpeed(0.6F);
                    } else if (DoubleSpeed == 6) {
                        player.setWalkSpeed(0.7F);
                    } else if (DoubleSpeed== 7) {
                        player.setWalkSpeed(0.8F);
                    } else if (DoubleSpeed == 8) {
                        player.setWalkSpeed(0.9F);
                    } else if (DoubleSpeed == 9) {
                        player.setWalkSpeed(1F);
                    } else if (DoubleSpeed == 10) {
                        player.setWalkSpeed(1F);
                    }
                    sender.sendMessage(ChatColor.GREEN + "Вы установили себе скорость " + DoubleSpeed);
                    return true;
                }
            }
            if (!sender.hasPermission("korshuntools.speed.others")) {
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
                    if (DoubleSpeedTarget == 0.5) {
                        target.setWalkSpeed(0.1F);
                    } else if (DoubleSpeedTarget == 1) {
                        target.setWalkSpeed(0.2F);
                    } else if (DoubleSpeedTarget == 2) {
                        target.setWalkSpeed(0.3F);
                    } else if (DoubleSpeedTarget == 3) {
                        target.setWalkSpeed(0.4F);
                    } else if (DoubleSpeedTarget == 4) {
                        target.setWalkSpeed(0.5F);
                    } else if (DoubleSpeedTarget == 5) {
                        target.setWalkSpeed(0.6F);
                    } else if (DoubleSpeedTarget == 6) {
                        target.setWalkSpeed(0.7F);
                    } else if (DoubleSpeedTarget == 7) {
                        target.setWalkSpeed(0.8F);
                    } else if (DoubleSpeedTarget == 8) {
                        target.setWalkSpeed(0.9F);
                    } else if (DoubleSpeedTarget == 9) {
                        target.setWalkSpeed(1F);
                    } else if (DoubleSpeedTarget == 10) {
                        target.setWalkSpeed(1F);
                    }
                    target.sendMessage(ChatColor.GREEN + "Вам установили скорость на " + DoubleSpeedTarget);
                    player.sendMessage(ChatColor.GREEN + "Вы установили игроку " + target.getName() + " скорость на значение " + DoubleSpeedTarget);
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.RED + "Неизвестный аргумент!");
        return false;
    }
}