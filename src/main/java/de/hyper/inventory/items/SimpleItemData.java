package de.hyper.inventory.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.Map;

/**
 * @author hyperspace_pilot
 */
public class SimpleItemData extends ItemData {

    public SimpleItemData(Material material) {
        super(material);
    }

    public SimpleItemData(Material material, int count, String displayName) {
        super(material, count, displayName);
    }

    public SimpleItemData(Material material, int count) {
        super(material, count);
    }

    public SimpleItemData(Material material, String displayName) {
        super(material, displayName);
    }

    public SimpleItemData(Material material, Map<Enchantment, Integer> enchantmentsAndLevels, int count, ItemFlag[] itemFlags, boolean unbreakable, int damage, String displayName, String lore[]) {
        super(material, enchantmentsAndLevels, count, itemFlags, unbreakable, damage, displayName, lore);
    }

    @Override
    public boolean transformStrings() {
        return true;
    }
}
