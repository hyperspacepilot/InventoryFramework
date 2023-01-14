package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.items.ItemData;

/**
 * @author hyperspace_pilot
 */
public class SquaredBackGroundDesign extends InventoryDesign {

    protected ItemData firstBackGroundItem;
    protected ItemData secondBackGroundItem;

    public SquaredBackGroundDesign(int rows, ItemData firstBackGroundItem, ItemData secondBackGroundItem) {
        super(rows);
        this.firstBackGroundItem = firstBackGroundItem;
        this.secondBackGroundItem = secondBackGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                items[a][b] = ((a % 2 == 0 && b % 2 == 0)
                        || (a % 2 == 1 && b % 2 == 1)) ? firstBackGroundItem : secondBackGroundItem;
            }
        }
    }
}
