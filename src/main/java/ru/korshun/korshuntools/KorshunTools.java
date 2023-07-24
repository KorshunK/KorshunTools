package ru.korshun.korshuntools;

import com.sun.security.auth.module.UnixLoginModule;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;
import ru.korshun.korshuntools.commands.*;
import ru.korshun.korshuntools.events.*;
import ru.korshun.korshuntools.tabcomplete.*;

import javax.swing.*;

public final class KorshunTools extends JavaPlugin {
    private static KorshunTools instance;
    private NickCommand nickCommand;

    @Override
    public void onEnable() {
        instance = this;
        new UpdateChecker(this, 110417).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("Обновлений не найдено");
            } else {
                getLogger().info("Найдено обновление! Скачать можно на сайте: https://www.spigotmc.org/resources/korshuntools.110417/");
            }
        });
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("fly").setTabCompleter(new FlyTabCompleter());
        getCommand("sethealth").setExecutor(new SetHealthCommand());
        getCommand("sethealth").setTabCompleter(new SetHealthTabCompleter());
        getCommand("setfood").setExecutor(new SetFoodCommand());
        getCommand("setfood").setTabCompleter(new SetFoodTabCompleter());
        getCommand("korshuntools").setExecutor(new KorshunToolsCommand());
        getCommand("korshuntools").setTabCompleter(new KorshunToolsTabCompleter());
        getLogger().info(ChatColor.GREEN + "Enabled!");
        getCommand("checkfly").setExecutor(new CheckFlyCommand());
        getCommand("checkhealth").setExecutor(new CheckHealthCommand());
        getCommand("checkfood").setExecutor(new CheckFoodCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("flyspeed").setExecutor(new FlySpeedCommand());
        getCommand("checkflyspeed").setExecutor(new CheckFlySpeedCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("teleport").setExecutor(new TPCommand());
        getCommand("tphere").setExecutor(new TPHereCommand());
        getCommand("checkspeed").setExecutor(new CheckSpeedCommand());
        getCommand("seeinventory").setExecutor(new SeeInvCommand());
        getCommand("gmcheck").setExecutor(new GmCheckCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("realname").setExecutor(new RealNameCommand());
        getCommand("nick").setTabCompleter(new NickCompleter());
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("ext").setExecutor(new ExtCommand());
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
        getServer().getPluginManager().registerEvents(new SeeInvClickEvent(), this);
        getServer().getPluginManager().registerEvents(new GmCheckInvClickEvent(), this);
        getServer().getPluginManager().registerEvents(new NickMenuEvent(), this);
        saveDefaultConfig();
    }
    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "Disabled!");
    }

    public static KorshunTools getInstance() {
        return instance;
    }
}
