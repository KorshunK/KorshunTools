package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.korshun.korshuntools.KorshunTools;

public class CheckHealthCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("korshuntools.checkhealth")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
            return false;
        }
        else {
            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Использование: /checkhealth <Игрок>");
                return false;
            }
            if(args.length == 1) {
                String targetname = args[0];
                Player target = Bukkit.getPlayer(targetname);
                double TargetHealthLevel = target.getHealth();
                if(target == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                    return true;
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.target-health-level").replace("{health_level}", String.valueOf(TargetHealthLevel))));
                return true;
            }
        }
        return true;
    }
}