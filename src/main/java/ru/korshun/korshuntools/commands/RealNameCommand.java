package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class RealNameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.realname")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.realname")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /realname <Игрок>");
                    return false;
                }
                if(args.length == 1) {
                    Player target = getPlayerByDisplayName(args[0]);
                    Player TargetName = Bukkit.getPlayer(args[0]);
                    if(target == null && TargetName == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return false;
                    }
                    if(target != null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.realname").replace("{player_name}", target.getName()).replace("{player_nick}", target.getDisplayName())));
                    }
                    if(TargetName != null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.realname").replace("{player_name}", TargetName.getName()).replace("{player_nick}", TargetName.getDisplayName())));
                    }
                }
            }
        }
        return false;
    }
    private Player getPlayerByDisplayName(String displayname) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getDisplayName().equals(displayname)) {
                return player;
            }
        }
        return null;
    }
}
