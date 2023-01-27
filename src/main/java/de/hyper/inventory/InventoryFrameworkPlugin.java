package de.hyper.inventory;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static de.hyper.inventory.InventoryFramework.createInventoryBuilder;
import static de.hyper.inventory.InventoryFramework.createInventoryManager;

/**
 * @author hyperspace_pilot
 */
public class InventoryFrameworkPlugin extends JavaPlugin {

    private InventoryManager inventoryManager;

    @Override
    public void onLoad() {
        new InventoryFramework().setUp();
    }

    @Override
    public void onEnable() {
        inventoryManager = createInventoryManager(this);
        this.getCommand("testinventory").setExecutor((commandSender, command, s, strings) -> {
            if (commandSender instanceof Player player) {
                if (player.hasPermission("inventoryframework.testinventory")) {
                    TestInventory testInventory = new TestInventory(player);
                    createInventoryBuilder(inventoryManager).buildInventory(testInventory);
                }
            }
            return false;
        });
    }

    @Override
    public void onDisable() {

    }
}
