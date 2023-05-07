package de.hyper.inventory;

import de.hyper.inventory.items.ItemData;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyperspace_pilot
 */
@Getter
public abstract class Inventory {

    protected Player player;
    protected String title;
    @Setter
    protected String rawTitle;
    protected int rows;
    @Setter
    protected InventoryDesign design;
    protected Map<Integer, InventoryButton> buttons;
    @Setter
    protected boolean animated = false;
    protected InventoryAnimation animation;
    @Setter
    protected boolean closeable = true;
    @Setter
    protected boolean created = false;
    @Setter
    protected InventoryBuilder inventoryBuilder;

    public Inventory(Player player, String title, int rows) {
        this.player = player;
        this.title = title;
        this.rows = rows;
        this.buttons = new HashMap<>();
        this.animation = new InventoryAnimation();
    }

    public Inventory(Player player, String title, int rows, boolean animated) {
        this.player = player;
        this.title = title;
        this.rows = rows;
        this.buttons = new HashMap<>();
        this.animated = animated;
        this.animation = new InventoryAnimation();
    }

    public void registerButtonAndItem(int row, int slot, InventoryButton inventoryButton, ItemData itemData) {
        registerButtonAndItem(row, slot, inventoryButton, itemData, 0L);
    }

    public void registerButtonAndItem(int row, int slot, InventoryButton inventoryButton, ItemData itemData, long timeToWait) {
        registerButtonAndItem(row * 9 + slot, inventoryButton, itemData, timeToWait);
    }

    public void registerButtonAndItem(int slot, InventoryButton inventoryButton, ItemData itemData) {
        registerButtonAndItem(slot, inventoryButton, itemData, 0L);
    }

    public void registerButtonAndItem(int slot, InventoryButton inventoryButton, ItemData itemData, long timeToWait) {
        registerButton(slot, inventoryButton);
        registerItem(slot, itemData, timeToWait);
    }

    public void registerButton(int row, int slot, InventoryButton inventoryButton) {
        registerButton(row * 9 + slot, inventoryButton);
    }

    public void registerButton(int slot, InventoryButton inventoryButton) {
        inventoryButton.onAdd();
        this.buttons.put(slot, inventoryButton);
    }

    public void registerItem(int row, int slot, ItemData itemData) {
        registerItem(row * 9 + slot, itemData);
    }

    public void registerItem(int slot, ItemData itemData) {
        registerItem(slot, itemData, 0L);
    }

    public void registerItem(int row, int slot, ItemData itemData, long timeToWait) {
        registerItem(row * 9 + slot, itemData, timeToWait);
    }

    public void registerItem(int slot, ItemData itemData, long timeToWait) {
        this.animation.registerSlot(slot, itemData, timeToWait);
    }

    public void clearSlot(int row, int slot, ItemData itemData) {
        clearSlot(row * 9 + slot, itemData);
    }

    public void clearSlot(int slot, ItemData itemData) {
        this.buttons.remove(slot);
        if (this.inventoryBuilder.bukkitInventory != null) {
            if (itemData != null) {
                this.inventoryBuilder.bukkitInventory.setItem(slot, itemData.build());
            } else {
                this.inventoryBuilder.bukkitInventory.setItem(slot, null);
            }
        }
    }

    public abstract Inventory fillInventory();

    public abstract Inventory clearInventory();

    public abstract void onOpen();

    public abstract void onClose();
}