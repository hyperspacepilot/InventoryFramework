package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.items.ItemData;

/**
 * @author hyperspace_pilot
 */
public class LeftLineBackGroundDesign extends InventoryDesign {

    protected ItemData backGroundItem;
    protected ItemData borderBackGroundItem;

    public LeftLineBackGroundDesign(
            int rows, ItemData backGroundItem, ItemData borderBackGroundItem) {
        super(rows);
        this.backGroundItem = backGroundItem;
        this.borderBackGroundItem = borderBackGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                items[a][b] = (b == 0) ? borderBackGroundItem : backGroundItem;
            }
        }
    }
}