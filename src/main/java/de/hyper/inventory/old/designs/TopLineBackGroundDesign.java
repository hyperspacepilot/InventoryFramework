package de.hyper.inventory.old.designs;

import de.hyper.inventory.old.InventoryDesign;
import org.bukkit.inventory.ItemStack;

public class TopLineBackGroundDesign extends InventoryDesign {

    protected ItemStack backGroundItemStack;
    protected ItemStack borderBackGroundItemStack;

    public TopLineBackGroundDesign(int rows, ItemStack backGroundItemStack, ItemStack borderBackGroundItemStack) {
        super(rows);
        this.backGroundItemStack = backGroundItemStack;
        this.borderBackGroundItemStack = borderBackGroundItemStack;
    }

    @Override
    public ItemStack[][] getLines() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b <= 8; b++) {
                lines[a][b] = (a == 0) ? borderBackGroundItemStack : backGroundItemStack;
            }
        }
        return lines;
    }
}
