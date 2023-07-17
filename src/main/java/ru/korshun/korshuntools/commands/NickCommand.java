package ru.korshun.korshuntools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

import java.util.Collections;

public class NickCommand implements CommandExecutor {
    public Inventory NickChangeInv = Bukkit.createInventory(null, 54, KorshunTools.getInstance().getConfig().getString("nick.menu-title"));


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            Player player = (Player) sender;
            ItemStack NewNick = new ItemStack(Material.GREEN_STAINED_GLASS);
            ItemStack ClearNick = new ItemStack(Material.RED_STAINED_GLASS);
            ItemStack InfoOfNick = new ItemStack(Material.BOOK);
            ItemMeta NewNickIM = NewNick.getItemMeta();
            NewNickIM.setDisplayName(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("nick.new-nick-display-name")));
            NewNick.setItemMeta(NewNickIM);
            ItemMeta ClearNickIM = ClearNick.getItemMeta();
            ClearNickIM.setDisplayName(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("nick.clear-nick-display-name")));
            ClearNick.setItemMeta(ClearNickIM);
            NickChangeInv.setItem(8, ClearNick);
            NickChangeInv.setItem(0, NewNick);
            if (!KorshunTools.getInstance().getConfig().getBoolean("commands.nick")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
                return false;
            } else {
                if (!sender.hasPermission("korshuntools.nick")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                    return false;
                } else {
                    if (!(sender instanceof Player)) {

                    } else {
                        if (args.length == 0) {
                            player.openInventory(NickChangeInv);
                        }
                        if (args.length == 1) {
                            player.setDisplayName(args[0]);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.change-nick").replace("{currency_nick}", player.getDisplayName())));
                            if(args[0].equalsIgnoreCase("clear")) {
                                player.setDisplayName(player.getName());
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.clear-nick")));
                            }
                        }
                    }
                }
            }
            return false;
        }
    }