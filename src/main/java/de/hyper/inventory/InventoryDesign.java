package de.hyper.inventory;

import de.hyper.inventory.items.ItemData;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hyperspace_pilot
 */
@Getter
public abstract class InventoryDesign {

    protected ItemData[][] items;
    protected int rows;
    @Setter
    protected boolean animated;

    public InventoryDesign(int rows) {
        this.rows = (rows <= 0 ? 1 : (rows > 6 ? 6 : rows));
        this.items = new ItemData[rows][9];
    }

    public abstract void registerItems();
}
