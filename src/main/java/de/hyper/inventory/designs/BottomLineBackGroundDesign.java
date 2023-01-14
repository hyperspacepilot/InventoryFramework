package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.items.ItemData;

/**
 * @author hyperspace_pilot
 */
public class BottomLineBackGroundDesign extends InventoryDesign {

    protected ItemData backGroundItem;
    protected ItemData borderBackGroundItem;

    public BottomLineBackGroundDesign(
            int rows, ItemData backGroundItem, ItemData borderBackGroundItem) {
        super(rows);
        this.backGroundItem = backGroundItem;
        this.borderBackGroundItem = borderBackGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                items[a][b] = (a == (rows-1)) ? borderBackGroundItem : backGroundItem;
            }
        }
    }
}
