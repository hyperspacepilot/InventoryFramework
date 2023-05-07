package de.hyper.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hyperspace_pilot
 */
public interface InventoryManager extends Listener {
    void aSync(Runnable runnable);

    Map<Player, CopyOnWriteArrayList<Inventory>> getPlayerInventories();

    Plugin getPlugin();
}