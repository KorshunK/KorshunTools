package ru.korshun.korshuntools.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.korshun.korshuntools.KorshunTools;

public class NickMenuEvent implements Listener {

    @EventHandler
    public void NickMenu(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(e.getView().getTitle() == KorshunTools.getInstance().getConfig().getString("nick.menu-title")) {
            e.setCancelled(true);
            if(e.getSlot() == 0) {
                e.getView().close();
                player.sendMessage(ChatColor.GREEN + "Для смены ника: /nick <Новый ник>");
            }
            if(e.getSlot() == 8) {
                e.getView().close();
                player.sendMessage(ChatColor.RED + "Для сбросса ника: /nick clear");
            }
        }
    }
}
