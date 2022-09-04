package de.hyper.inventory.buttons;

import de.hyper.inventory.InventoryManager;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

@AllArgsConstructor
public abstract class FetchChatMessageButton extends Button implements Listener {

    private Plugin plugin;
    protected Player player;

    @Override
    public void onClick(InventoryAction inventoryAction) {
        player.closeInventory();
        Bukkit.getPluginManager().registerEvents(this, InventoryManager.getInstance(this.plugin.getClass()).getPlugin());
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().getUniqueId().equals(player.getUniqueId())) {
            onReceiveMessage(event.getMessage());
            event.setCancelled(true);
            HandlerList.unregisterAll(this);
        }
    }

    public abstract void onReceiveMessage(String message);
}