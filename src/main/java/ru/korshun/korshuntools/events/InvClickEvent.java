package ru.korshun.korshuntools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.korshun.korshuntools.KorshunTools;

public class InvClickEvent implements Listener {
    @EventHandler
    public void InvClick(InventoryClickEvent e) {
        if(e.getView().getTitle() == KorshunTools.getInstance().getConfig().getString("messages.checkfly-menu-title")) {
            e.setCancelled(true);
        }
        if(e.getInventory().getTitle() == KorshunTools.getInstance().getConfig().getString("messages.checkfly-menu-title")) {
            e.setCancelled(true);
        }
    }
}
