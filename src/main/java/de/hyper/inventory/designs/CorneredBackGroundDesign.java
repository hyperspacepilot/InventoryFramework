package de.hyper.inventory.designs;

import de.hyper.inventory.InventoryDesign;
import de.hyper.inventory.ItemStackData;

public class CorneredBackGroundDesign extends InventoryDesign {

    protected ItemStackData backGroundItem;
    protected ItemStackData inCornerBackGroundItem;
    protected ItemStackData outCornerBackGroundItem;

    public CorneredBackGroundDesign(
            int rows, ItemStackData backGroundItem,
            ItemStackData inCornerBackGroundItem, ItemStackData outCornerBackGroundItem) {
        super((rows < 4 ? 4 : (rows > 5 ? 5 : rows)));
        this.backGroundItem = backGroundItem;
        this.inCornerBackGroundItem = inCornerBackGroundItem;
        this.outCornerBackGroundItem = outCornerBackGroundItem;
    }

    @Override
    public void registerItems() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                items[a][b] = (((a == 0 && (b == 0 || b == 1 || b == 7 || b == 8))
                        || (a == 1 && (b == 0 || b == 8))
                        || (a == (rows - 2) && (b == 0 || b == 8)) || (a == (rows-1) &&
                        (b == 0 || b == 1 || b == 7 || b == 8))) ?
                        ((((a == 0 || a == (rows-1)) && (b == 0 || b == 8))) ?
                                inCornerBackGroundItem : outCornerBackGroundItem)
                        : backGroundItem);
            }
        }
    }
}
