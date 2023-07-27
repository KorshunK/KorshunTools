package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class BurnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        KorshunTools plugin = KorshunTools.getInstance();
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.burn")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        }
        else {
            if (!sender.hasPermission("korshuntools.burn")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /burn <Игрок>");
                    return false;
                }
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFireTicks(60);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.burn-player").replace("{player_name}", target.getName()).replace("{burn_time}", String.valueOf(target.getFireTicks()))));
                }
                if(args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFireTicks(Integer.parseInt(args[1]));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.burn-player").replace("{player_name}", target.getName()).replace("{burn_time}", String.valueOf(target.getFireTicks()))));
                }
            }
        }
        return false;
    }
}
