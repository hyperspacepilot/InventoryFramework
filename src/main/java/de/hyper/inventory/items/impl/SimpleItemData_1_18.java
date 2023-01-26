package de.hyper.inventory.items.impl;

import org.bukkit.Material;

/**
 * @author hyperspace_pilot
 */
public class SimpleItemData_1_18 extends ItemData_1_18 {

    public SimpleItemData_1_18(Material material) {
        super(material);
    }
    @Override
    public boolean transformStrings() {
        return true;
    }
}
