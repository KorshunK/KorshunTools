package ru.korshun.korshuntools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class GmCheckInvClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle() == "Проверка режима") {
            e.setCancelled(true);
        }
    }
}
