package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.korshun.korshuntools.KorshunTools;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission("korshuntools.fly")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
            return false;
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.sender-not-player")));
                return false;
            }
            else {
                if (args.length == 0) {
                    if (player.getAllowFlight() == false) {
                        player.setAllowFlight(true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.fly-enable")));
                        return true;
                    } else {
                        player.setAllowFlight(false);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.fly-disable")));
                        return true;
                    }
                }
            }
        }
        if (!sender.hasPermission("korshuntools.fly.others")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
        } else {
            if (args.length == 1) {
                String targetname = args[0];
                Player target = Bukkit.getPlayer(targetname);
                if (target == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                    return false;
                } else {
                    if (!target.getAllowFlight()) {
                        target.setAllowFlight(true);
                        sender.sendMessage(ChatColor.GREEN + "Вы включили режим полета игроку " + target);
                        target.sendMessage(ChatColor.GREEN + "Вам включили режим полета");
                        return true;
                    } else {
                        target.setAllowFlight(false);
                        sender.sendMessage(ChatColor.RED + "Вы отключили режим полета игроку " + target);
                        target.sendMessage(ChatColor.RED + "Вам отключили режим полета");
                        return true;
                    }
                }
            }
        }
        if(args.length == 2) {
            String targetname = args[0];
            Player target = Bukkit.getPlayer(targetname);
            if(target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                return false;
            }
            else {
                if(args[1].equalsIgnoreCase("true")) {
                    target.setAllowFlight(true);
                    sender.sendMessage(ChatColor.GREEN + "Вы включили режим полета игроку " + target);
                    target.sendMessage(ChatColor.GREEN + "Вам включили режим полета");
                    return true;
                }
                if(args[1].equalsIgnoreCase("false")) {
                    target.setAllowFlight(false);
                    sender.sendMessage(ChatColor.RED + "Вы отключили режим полета игроку " + target);
                    target.sendMessage(ChatColor.RED + "Вам отключили режим полета");
                    return true;
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Неизвестный аргумент!");
                    return false;
                }
            }
        }
        return true;
    }
}