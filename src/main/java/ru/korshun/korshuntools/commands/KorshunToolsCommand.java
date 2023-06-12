package ru.korshun.korshuntools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ru.korshun.korshuntools.KorshunTools;

public class KorshunToolsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "Версия: 1.3");
            sender.sendMessage(ChatColor.RED + "Для помощи: /kt help");
            return true;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("korshuntools.korshuntools.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', KorshunTools.getInstance().getConfig().getString("messages.no-permissions")));
                return false;
            } else if (sender.hasPermission("korshuntools.korshuntools.reload")) {
                KorshunTools.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Плагин перезагружен!");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.GREEN + "Команда плагина: /korshuntools");
            sender.sendMessage(ChatColor.GREEN + "Включить режим полета: /fly");
            sender.sendMessage(ChatColor.GREEN + "Установить уровень здоровья игроку: /sethealth <Игрок> <Уровень>");
            sender.sendMessage(ChatColor.GREEN + "Установить уровень еды игроку: /setfood <Игрок> <Уровень>");
            sender.sendMessage(ChatColor.GREEN + "Проверить, включен ли режим полета у игрока: /checkfly <Игрок>");
            sender.sendMessage(ChatColor.GREEN + "Проверить уровень здоровья игрока: /checkhealth <Игрок>");
            sender.sendMessage(ChatColor.GREEN + "Проверить уровень еды игрока: /checkfood <Игрок>");
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "Неизвестный аргумент!");
        }
        return true;
    }
}