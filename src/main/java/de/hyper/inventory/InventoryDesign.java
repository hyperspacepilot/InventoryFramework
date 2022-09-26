package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class InventoryDesign {

    protected ItemStackData[][] items;
    protected int rows;
    @Setter
    protected boolean animated;

    public InventoryDesign(int rows) {
        this.rows = (rows <= 0 ? 1 : (rows > 6 ? 6 : rows));
        this.items = new ItemStackData[rows][9];
    }

    public abstract void registerItems();
}
