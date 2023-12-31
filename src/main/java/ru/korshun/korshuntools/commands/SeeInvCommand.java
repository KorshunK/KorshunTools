package ru.korshun.korshuntools.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class SeeInvCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Inventory targetSeeInv = Bukkit.createInventory(null, 54, "hbgfgh");
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.seeinv")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.sender-not-player")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.seeinventory")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /seeinv <Игрок>");
                    return false;
                }
                else if (args.length == 1) {
                    Player player = (Player) sender;
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    if(player == target) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.seeinv-target-is-player")));
                    }
                    else if(player != target) {
                        Inventory targetInv = target.getInventory();
                        targetSeeInv.addItem((ItemStack) targetInv);
                        player.updateInventory();
                        player.openInventory(targetSeeInv);
                        player.updateInventory();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.you-see-inv").replace("{player_name}", player.getName())));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
