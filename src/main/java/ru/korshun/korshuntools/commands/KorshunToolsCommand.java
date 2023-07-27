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
            sender.sendMessage(ChatColor.GREEN + "Версия: " + KorshunTools.getInstance().getServer().getPluginManager().getPlugin("KorshunTools").getDescription().getVersion());
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
            sender.sendMessage(ChatColor.GREEN + "Вылечить себя или игрока: /heal");
            sender.sendMessage(ChatColor.GREEN + "Покормить себя или игрока: /feed");
            sender.sendMessage(ChatColor.GREEN + "Установить скорость полета: /flyspeed");
            sender.sendMessage(ChatColor.GREEN + "Проверить скорость полета игрока: /checkflyspeed");
            sender.sendMessage(ChatColor.GREEN + "Установить скорость: /speed");
            sender.sendMessage(ChatColor.GREEN + "Телепортация: /tp");
            sender.sendMessage(ChatColor.GREEN + "Телепортировать игрока к себе: /tphere");
            sender.sendMessage(ChatColor.GREEN + "Проверить скорость игрока: /checkspeed");
            sender.sendMessage(ChatColor.GREEN + "Посмотреть инвентарь игрока: /invsee");
            sender.sendMessage(ChatColor.GREEN + "Проверить режим игрока: /gmcheck");
            sender.sendMessage(ChatColor.GREEN + "Изменить ник: /nick");
            sender.sendMessage(ChatColor.GREEN + "Узнать настоящий ник игрока: /realname");
            sender.sendMessage(ChatColor.GREEN + "Отчистить инвентарь: /clear");
            sender.sendMessage(ChatColor.GREEN + "Поджечь игрока: /burn");
            sender.sendMessage(ChatColor.GREEN + "Потушить игрока: /ext");
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "Неизвестный аргумент!");
        }
        return true;
    }
}