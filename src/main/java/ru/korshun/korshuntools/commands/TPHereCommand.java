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

public class TPHereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.tphere")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        }
        else {
            if (!sender.hasPermission("korshuntools.tphere")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /tphere <Игрок>");
                    return false;
                }
                if(args.length == 1) {
                    Player player = (Player) sender;
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    Location PlayerLoc = player.getLocation();
                    if(target == player) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.tphere-target-is-player")));
                        return false;
                    }
                    target.teleport(PlayerLoc);
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.tphere-target").replace("{player_name}", player.getName())));
                    return true;
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Введите команду правильно!");
                }
            }
        }
        sender.sendMessage(ChatColor.RED + "Неизвестный аргумент!");
        return false;
    }
}
