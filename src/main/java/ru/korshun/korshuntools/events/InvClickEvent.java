package ru.korshun.korshuntools.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import ru.korshun.korshuntools.KorshunTools;
import ru.korshun.korshuntools.commands.CheckFlyCommand;

import java.io.StringReader;

public class InvClickEvent implements Listener {
    @EventHandler
    public void InvClick(InventoryClickEvent e) {
            if (e.getView().getTitle() == KorshunTools.getInstance().getConfig().getString("messages.checkfly-menu-title")) {
                e.setCancelled(true);
            }
    }
}