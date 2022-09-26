package de.hyper.inventory;

import de.hyper.inventory.buttons.Button;
import de.hyper.inventory.designs.TopBottomLineBackGroundDesign;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

import java.util.List;

public abstract class ChooseInventory<T> extends Inventory {

    private List<T> list;

    public ChooseInventory(Player player, int rows, List<T> list) {
        super(player, "Choose", rows, false);
        this.setDesign(new TopBottomLineBackGroundDesign(this.getRows(), null, GlassPane.C7));
        this.list = list;
    }

    @Override
    public Inventory fillInventory() {
        int row = 2;
        int slot = 0;
        for (T t : list) {
            registerButtonAndItem(row, slot, new Button() {
                @Override
                public void onClick(InventoryAction inventoryAction) {
                    setCloseable(true);
                    selected(t);
                }
            }, buildItemStackData(t));
            slot++;
            if (slot > 8) {
                row++;
                slot = 0;
            }
        }
        return this;
    }

    @Override
    public void onOpen() {
    }

    @Override
    public void onClose() {
    }

    public abstract void selected(T t);

    public abstract ItemStackData buildItemStackData(T t);
}