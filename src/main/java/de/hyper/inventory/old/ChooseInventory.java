package de.hyper.inventory.old;

import de.hyper.inventory.old.buttons.Button;
import de.hyper.inventory.old.designs.TopBottomLineBackGroundDesign;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public abstract class ChooseInventory<T> extends Inventory {

    private List<T> list;

    public ChooseInventory(Plugin plugin, int rows, List<T> list) {
        super(plugin, "Choose", rows, false);
        this.setDesign(new TopBottomLineBackGroundDesign(this.getRows(), null, GlassPane.C7));
        this.list = list;
    }

    @Override
    public Inventory fillInventory() {
        int row = 2;
        int slot = 0;
        for (T t : list) {
            registerButton(row, slot, new Button() {
                @Override
                public void onClick(InventoryAction inventoryAction) {
                    setCloseable(true);
                    selected(t);
                }
            }, buildItem(t));
            slot++;
            if (slot > 8) {
                row++;
                slot = 0;
            }
        }
        return this;
    }

    @Override
    public Inventory cleanInventory() {
        return this;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

    public abstract void selected(T t);

    public abstract ItemStack buildItem(T t);
}
