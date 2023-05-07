package de.hyper.inventory.items.old;

import org.bukkit.Material;

/**
 * @author hyperspace_pilot
 */
public class SimpleItemData extends ItemData {

    public SimpleItemData(Material material) {
        super(material);
    }

    @Override
    public boolean transformStrings() {
        return true;
    }
}