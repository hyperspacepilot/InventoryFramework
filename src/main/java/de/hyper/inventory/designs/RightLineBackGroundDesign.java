package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.ItemStackData;

public class RightLineBackGroundDesign extends InventoryDesign {

    protected ItemStackData backGroundItem;
    protected ItemStackData borderBackGroundItem;

    public RightLineBackGroundDesign(int rows, ItemStackData backGroundItem, ItemStackData borderBackGroundItem) {
        super(rows);
        this.backGroundItem = backGroundItem;
        this.borderBackGroundItem = borderBackGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                items[a][b] = (b == 8) ? borderBackGroundItem : backGroundItem;
            }
        }
    }
}
