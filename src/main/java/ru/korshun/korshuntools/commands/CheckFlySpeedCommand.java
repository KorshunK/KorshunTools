package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class CheckFlySpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.flyspeed")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.checkflyspeed")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "/checkflyspeed <Игрок>");
                    return false;
                }
                if (args.length == 1) {
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return false;
                    }
                    Float TargetFlySpeed = target.getFlySpeed();
                    if (TargetFlySpeed == Float.parseFloat("0.1")) {
                        sender.sendMessage(ChatColor.GREEN + "1");
                    } else if (TargetFlySpeed == Float.parseFloat("0.2")) {
                        sender.sendMessage(ChatColor.GREEN + "2");
                    } else if (TargetFlySpeed == Float.parseFloat("0.3")) {
                        sender.sendMessage(ChatColor.GREEN + "3");
                    } else if (TargetFlySpeed == Float.parseFloat("0.4")) {
                        sender.sendMessage(ChatColor.GREEN + "4");
                    } else if (TargetFlySpeed == Float.parseFloat("0.5")) {
                        sender.sendMessage(ChatColor.GREEN + "5");
                    } else if (TargetFlySpeed == Float.parseFloat("0.6")) {
                        sender.sendMessage(ChatColor.GREEN + "6");
                    } else if (TargetFlySpeed == Float.parseFloat("0.7")) {
                        sender.sendMessage(ChatColor.GREEN + "7");
                    } else if (TargetFlySpeed == Float.parseFloat("0.8")) {
                        sender.sendMessage(ChatColor.GREEN + "8");
                    } else if (TargetFlySpeed == Float.parseFloat("0.9")) {
                        sender.sendMessage(ChatColor.GREEN + "9");
                    } else if (TargetFlySpeed == Float.parseFloat("1")) {
                        sender.sendMessage(ChatColor.GREEN + "10");
                    }
                    return true;
                }
            }
            return true;
        }
    }
}
