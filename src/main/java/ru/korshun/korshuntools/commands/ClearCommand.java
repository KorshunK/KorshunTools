package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class ClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.clear")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.clear")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    Player player = (Player) sender;
                    clearInventory(player);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.clear-inv")));
                }
            }
            if(!sender.hasPermission("korshuntools.clear.others")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(!(sender instanceof Player)) {
                    if(args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "Использование: /clear <Игрок>");
                    }
                    if(args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                            return false;
                        }
                        clearInventory(target);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.clear-inv-other").replace("{player_name}", target.getName())));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.target-clear-inv")));
                    }
                }
                else {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                            return false;
                        }
                        clearInventory(target);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.clear-inv-other").replace("{player_name}", target.getName())));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.target-clear-inv")));
                    }
                }
            }
        }
        return false;
    }
    private void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }
}
