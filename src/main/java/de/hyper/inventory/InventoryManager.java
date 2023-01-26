package de.hyper.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author hyperspace_pilot
 */
public interface InventoryManager extends Listener {
    void aSync(Runnable runnable);

    Map<Player, ArrayList<Inventory>> getPlayerInventories();
}