package de.hyper.inventory.managers;

import de.hyper.inventory.Inventory;
import de.hyper.inventory.InventoryButton;
import de.hyper.inventory.InventoryManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author hyperspace_pilot
 */
public class InventoryManager_1_18 implements InventoryManager {

    private Plugin plugin;
    private Map<Player, ArrayList<Inventory>> playerInventories;
    private Executor threadPool;

    public InventoryManager_1_18(Plugin plugin) {
        this.plugin = plugin;
        this.playerInventories = new HashMap<>();
        this.threadPool = Executors.newCachedThreadPool();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void aSync(Runnable runnable) {
        this.threadPool.execute(runnable);
    }

    @Override
    public Map<Player, ArrayList<Inventory>> getPlayerInventories() {
        return playerInventories;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        if (playerInventories.containsKey(player)) {
            ArrayList<Inventory> inventories = playerInventories.get(player);
            for (Inventory inventory : inventories) {
                if (inventory.getRawTitle().equals(view.getTitle())) {
                    if (inventory.getButtons().containsKey(event.getSlot())) {
                        if (inventory.isAnimated() && inventory.getAnimation().isAnimating()) {
                            event.setCancelled(true);
                            return;
                        }
                        InventoryButton inventoryButton = inventory.getButtons().get(event.getSlot());
                        event.setCancelled(inventoryButton.isCancelClick());
                        inventoryButton.click(player, event.getAction());
                    } else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        InventoryView view = event.getView();
        if (playerInventories.containsKey(player)) {
            ArrayList<Inventory> inventories = playerInventories.get(player);
            for (Inventory inventory : inventories) {
                if (inventory.getRawTitle().equals(view.getTitle())) {
                    if (inventory.isAnimated() && inventory.getAnimation().isAnimating()) {
                        player.openInventory(inventory.getInventoryBuilder().getBukkitInventory());
                        return;
                    }
                    if (inventory.isCloseable()) {
                        inventories.remove(inventory);
                        inventory.onClose();
                    } else {
                        player.openInventory(inventory.getInventoryBuilder().getBukkitInventory());
                    }
                }
            }
        }
    }
}