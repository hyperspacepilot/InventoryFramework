package de.hyper.inventory.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.Map;

/**
 * @author hyperspace_pilot
 */
public abstract class LanguagedItemData extends ItemData {
    public LanguagedItemData(Material material) {
        super(material);
    }

    public LanguagedItemData(Material material, int count, String displayName) {
        super(material, count, displayName);
    }

    public LanguagedItemData(Material material, int count) {
        super(material, count);
    }

    public LanguagedItemData(Material material, String displayName) {
        super(material, displayName);
    }

    public LanguagedItemData(Material material, Map<Enchantment, Integer> enchantmentsAndLevels, int count, ItemFlag[] itemFlags, boolean unbreakable, int damage, String displayName, String lore[]) {
        super(material, enchantmentsAndLevels, count, itemFlags, unbreakable, damage, displayName, lore);
    }

    @Override
    public boolean transformStrings() {
        try {
            this.displayName = getText(displayName);
            int i = 0;
            for (String s : this.lore) {
                this.lore[i] = getText(this.lore[i]);
                i++;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public abstract String getText(String key);
}
