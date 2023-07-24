package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class ExtCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.ext")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.ext")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(!(sender instanceof Player)) {
                    if(args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "Использование: /ext <Игрок>");
                        return false;
                    }
                    if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setFireTicks(0);
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.ext-target").replace("{player_name}", target.getName())));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.ext-target-me")));
                        return true;
                    }
                }
                else {
                    if(args.length == 0) {
                        Player player = (Player) sender;
                        player.setFireTicks(0);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.ext-me")));
                    }
                    if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setFireTicks(0);
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.ext-target").replace("{player_name}", target.getName())));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.ext-target-me")));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
