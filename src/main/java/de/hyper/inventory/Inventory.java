package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Inventory {

    @Setter
    private String title;
    private final String id;
    private final int rows;
    @Setter
    private InventoryDesign design;
    private final Map<Integer, InventoryButton> buttons;
    @Setter
    private boolean closeable;
    @Setter
    private boolean instantDelete = true;
    protected org.bukkit.inventory.Inventory inventory;
    protected Player player;
    private Plugin plugin;

    public Inventory(Plugin plugin, String title, int rows) {
        this.plugin = plugin;
        this.title = title;
        this.id = InventoryManager.getInstance(this.getPlugin().getClass()).getNewID();
        this.rows = (rows < 1 ? 1 : (rows > 6 ? 6 : rows));
        this.design = null;
        this.buttons = new HashMap<>();
        this.closeable = true;
        this.inventory = null;
        this.player = null;
        createInventory();
        InventoryManager.getInstance(this.plugin.getClass()).getInventories().put(id, this);
    }

    public Inventory(Plugin plugin, String title, int rows, boolean closeable) {
        this.plugin = plugin;
        this.title = title;
        this.id = InventoryManager.getInstance(this.plugin.getClass()).getNewID();
        this.rows = (rows < 1 ? 1 : (rows > 6 ? 6 : rows));
        this.design = null;
        this.buttons = new HashMap<>();
        this.closeable = closeable;
        this.inventory = null;
        this.player = null;
        createInventory();
        InventoryManager.getInstance(this.getPlugin().getClass()).getInventories().put(id, this);
    }

    public void createInventory() {
        if (inventory == null) {
            inventory = Bukkit.createInventory(null, rows * 9,
                    InventoryManager.getInstance(this.plugin.getClass())
                            .getInventoryIdentifier() + id + (title.startsWith("§") || id.endsWith("0") ? title : "§0" + title));
        }
        if (this.design != null) {
            ItemStack[][] lines = design.getLines();
            for (int i = 1; i <= rows; i++) {
                ItemStack[] line = lines[i - 1];
                if (line != null) {
                    for (int a = 0; a < 9; a++) {
                        if (a + 1 <= line.length) {
                            if (inventory.getItem(((i - 1) * 9) + a) == null) {
                                inventory.setItem(((i - 1) * 9) + a, line[a]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void registerButton(int row, int slot, InventoryButton button, ItemStack itemStack) {
        registerButton((((row == 0 ? 1 : (row > 6 ? 6 : row)) - 1) * 9) + slot, button, itemStack);
    }

    public void registerButton(int row, int slot, InventoryButton button) {
        registerButton((((row == 0 ? 1 : (row > 6 ? 6 : row)) - 1) * 9) + slot, button);
    }

    public void registerButton(int slot, InventoryButton button, ItemStack itemStack) {
        registerButton(slot, button);
        if (slot < (this.rows * 9)) {
            inventory.setItem(slot, itemStack);
        }
    }

    public void registerButton(int slot, InventoryButton button) {
        if (button != null) {
            buttons.put(slot, button);
            button.add(this);
        }
    }

    public Inventory open(Player player) {
        this.player = player;
        createInventory();
        Bukkit.getScheduler().scheduleSyncDelayedTask(
                InventoryManager.getInstance(this.getPlugin().getClass()).getPlugin(), () -> {
            player.closeInventory();
            player.openInventory(inventory);
        });
        onOpen();
        return this;
    }

    public abstract Inventory fillInventory();

    public abstract Inventory cleanInventory();

    public abstract void onOpen();

    public abstract void onClose();

}