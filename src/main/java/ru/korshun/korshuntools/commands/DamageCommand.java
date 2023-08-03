package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class DamageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.damage")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.damage")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }

            else {
                if(!(sender instanceof Player) || sender instanceof Player) {
                    if(args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "Использование: /damage <Игрок> <Урон>");
                        return false;
                    }
                    if(args.length == 1) {
                        sender.sendMessage(ChatColor.RED + "Укажите урон");
                        return false;
                    }
                    if(args.length == 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target == null) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                            return false;
                        }
                        target.damage(Double.parseDouble(args[1]));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.damage-player").replace("{player_name}", target.getName()).replace("{damage}", args[1])));
                    }
                }
            }
        }
        return false;
    }
}
