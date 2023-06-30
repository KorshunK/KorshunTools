package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class TPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.tp")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.teleport")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /tp <Игрок> <Куда телепортировать>");
                    return false;
                }
                if(args.length == 1) {
                    Player player = (Player) sender;
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    Location TargetLoc = target.getLocation();
                    if(target == player) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.tp-target-is-player")));
                        return false;
                    }
                    player.teleport(TargetLoc);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.tp-to-player").replace("{player_name}", player.getName())));
                    return true;
                }
                if(args.length == 2) {
                    Player player = (Player) sender;
                    String targetname = args[1];
                    Player target = Bukkit.getPlayer(targetname);
                    Player WhoTeleport = Bukkit.getPlayer(args[0]);
                    Location TargetLoc = target.getLocation();
                    if(target == player) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.tp-target-is-player")));
                        return false;
                    }
                    WhoTeleport.teleport(TargetLoc);
                    WhoTeleport.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.tp-to-player").replace("{player_name}", player.getName())));
                    return true;
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Введите команду правильно!");
                }
            }
        }
        return false;
    }
}
