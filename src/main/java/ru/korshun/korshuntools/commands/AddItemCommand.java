package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class AddItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.additem")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return false;
        }
        else {
            if (!sender.hasPermission("korshuntools.additem")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            }
            else {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /additem <Игрок>");
                    return false;
                }
                if(args.length == 1) {
                    Player player = (Player) sender;
                    Player target = Bukkit.getPlayer(args[0]);
                    ItemStack targetAddItemStack = player.getItemInHand();
                    targetAddItemStack.setAmount(targetAddItemStack.getAmount());
                    AddItem(target, targetAddItemStack);
                }
            }
        }
        return false;
    }
    public void AddItem(Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }
}
