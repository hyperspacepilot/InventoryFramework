package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

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

    public void registerButtonAndItem(int row, int slot, InventoryButton inventoryButton, ItemStackData itemStackData) {
        registerButtonAndItem(row, slot, inventoryButton, itemStackData, 0L);
    }

    public void registerButtonAndItem(int row, int slot, InventoryButton inventoryButton, ItemStackData itemStackData, long timeToWait) {
        registerButtonAndItem(row * 9 + slot, inventoryButton, itemStackData, timeToWait);
    }

    public void registerButtonAndItem(int slot, InventoryButton inventoryButton, ItemStackData itemStackData) {
        registerButtonAndItem(slot, inventoryButton, itemStackData, 0L);
    }

    public void registerButtonAndItem(int slot, InventoryButton inventoryButton, ItemStackData itemStackData, long timeToWait) {
        registerButton(slot, inventoryButton);
        registerItem(slot, itemStackData, timeToWait);
    }

    public void registerButton(int row, int slot, InventoryButton inventoryButton) {
        registerButton(row * 9 + slot, inventoryButton);
    }

    public void registerButton(int slot, InventoryButton inventoryButton) {
        inventoryButton.onAdd();
        this.buttons.put(slot, inventoryButton);
    }

    public void registerItem(int row, int slot, ItemStackData itemStackData) {
        registerItem(row * 9 + slot, itemStackData);
    }

    public void registerItem(int slot, ItemStackData itemStackData) {
        registerItem(slot, itemStackData, 0L);
    }

    public void registerItem(int row, int slot, ItemStackData itemStackData, long timeToWait) {
        registerItem(row * 9 + slot, itemStackData, timeToWait);
    }

    public void registerItem(int slot, ItemStackData itemStackData, long timeToWait) {
        this.animation.registerSlot(slot, itemStackData, timeToWait);
    }

    public void clearSlot(int row, int slot, ItemStackData itemStackData) {
        clearSlot(row * 9 + slot, itemStackData);
    }

    public void clearSlot(int slot, ItemStackData itemStackData) {
        this.inventoryBuilder.bukkitInventory.setItem(slot, itemStackData.build());
    }

    public abstract Inventory fillInventory();

    public abstract Inventory clearInventory();

    public abstract void onOpen();

    public abstract void onClose();
}