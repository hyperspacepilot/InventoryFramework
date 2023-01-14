package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.items.ItemData;

/**
 * @author hyperspace_pilot
 */
public class CleanBackGroundDesign extends InventoryDesign {

    protected ItemData backGroundItem;

    public CleanBackGroundDesign(int rows, ItemData backGroundItem) {
        super(rows);
        this.backGroundItem = backGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < 9; b++) {
                items[a][b] = backGroundItem;
            }
        }
    }
}