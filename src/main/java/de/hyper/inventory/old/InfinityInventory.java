package de.hyper.inventory.old;

import de.hyper.inventory.old.buttons.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

@Getter
@Setter
public abstract class InfinityInventory<T> extends Inventory {

    protected List<T> list;
    protected int currentPage;
    protected double maxPage;

    public InfinityInventory(Plugin plugin, String title, int rows, boolean closeable) {
        super(plugin, title, rows, closeable);
    }

    public void registerNextPageButton(int row, int slot, ItemStack itemStack) {
        registerButton(row, slot, new Button() {
            @Override
            public void onClick(InventoryAction inventoryAction) {
                currentPage++;
                cleanInventory().fillInventory();
            }
        }, itemStack);
    }

    public void registerLastPageButton(int row, int slot, ItemStack itemStack) {
        registerButton(row, slot, new Button() {
            @Override
            public void onClick(InventoryAction inventoryAction) {
                currentPage--;
                cleanInventory().fillInventory();
            }
        }, itemStack);
    }
}