package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.ItemStackData;

public class TopLineBackGroundDesign extends InventoryDesign {

    protected ItemStackData backGroundItem;
    protected ItemStackData borderBackGroundItem;

    public TopLineBackGroundDesign(int rows, ItemStackData backGroundItem, ItemStackData borderBackGroundItem) {
        super(rows);
        this.backGroundItem = backGroundItem;
        this.borderBackGroundItem = borderBackGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                items[a][b] = (a == 0) ? borderBackGroundItem : backGroundItem;
            }
        }
    }
}
