package ru.korshun.korshuntools.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import ru.korshun.korshuntools.KorshunTools;

public class CheckFlyCommand implements CommandExecutor {
    private static CheckFlyCommand instance;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Inventory CheckFlyInv = Bukkit.createInventory(null, 45, KorshunTools.getInstance().getConfig().getString("messages.checkfly-menu-title"));
        if (!KorshunTools.getInstance().getConfig().getBoolean("commands.checkfly")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.command-disable")));
            return true;
        } else {
            if (!sender.hasPermission("korshuntools.checkfly")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Использование: /checkfly <Игрок>");
                }
                if (args.length == 1) {
                    String targetname = args[0];
                    Player target = Bukkit.getPlayer(targetname);
                    if (target == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.player-not-found")));
                        return false;
                    }
                    if (!player.getAllowFlight()) {
                        Material FlyDisableMaterial = Material.valueOf(KorshunTools.getInstance().getConfig().getString("fly-disable-material"));
                        ItemStack FlyDisable = new ItemStack(FlyDisableMaterial);
                        ItemMeta FlyDisableIM = FlyDisable.getItemMeta();
                        FlyDisableIM.setDisplayName(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("fly-disable-displayname")));
                        FlyDisableIM.getPersistentDataContainer().set(NamespacedKey.fromString("target"), PersistentDataType.STRING, target.getName());
                        FlyDisable.setItemMeta(FlyDisableIM);
                        CheckFlyInv.setItem(22, FlyDisable);
                        player.openInventory(CheckFlyInv);
                    }
                    if (player.getAllowFlight()) {
                        Material FlyEnableMaterial = Material.valueOf(KorshunTools.getInstance().getConfig().getString("fly-enable-material"));
                        ItemStack FlyEnable = new ItemStack(FlyEnableMaterial);
                        ItemMeta FlyEnableIM = FlyEnable.getItemMeta();
                        FlyEnableIM.setDisplayName(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("fly-enable-displayname")));
                        FlyEnableIM.getPersistentDataContainer().set(NamespacedKey.fromString("target"), PersistentDataType.STRING, target.getName());
                        FlyEnable.setItemMeta(FlyEnableIM);
                        CheckFlyInv.setItem(22, FlyEnable);
                        player.openInventory(CheckFlyInv);
                    }
                }
            }
            return true;
        }
    }

    public static CheckFlyCommand getInstance() {
        return instance;
    }
}