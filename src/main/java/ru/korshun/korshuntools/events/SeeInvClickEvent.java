package ru.korshun.korshuntools.events;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SeeInvClickEvent implements Listener {
    @EventHandler
    public void onInvSeeClick(InventoryClickEvent e) {
        if(!e.getWhoClicked().hasPermission("korshuntools.seeinventory.editinventory") && e.getView().getTitle() == "Player") {
            e.setCancelled(true);
        }
    }
}
