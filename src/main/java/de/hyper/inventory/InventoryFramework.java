package de.hyper.inventory;

import org.bukkit.plugin.Plugin;

public class InventoryFramework {

    public static InventoryManager createInventoryManager(Plugin plugin) {
        return new InventoryManager(plugin);
    }

    public static InventoryBuilder createInventoryBuilder(InventoryManager inventoryManager) {
        return new InventoryBuilder(inventoryManager);
    }
}
