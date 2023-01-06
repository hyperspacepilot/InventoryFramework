package de.hyper.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import java.util.Map;

public class SimpleItemStackData extends ItemStackData {

    public SimpleItemStackData(String displayName, String[] lore, int amount, Material material, ItemFlag[] itemFlags, boolean unbreakable, Map<Enchantment, Integer> enchantments, int damage) {
        super(displayName, lore, amount, material, itemFlags, unbreakable, enchantments, damage);
    }

    public SimpleItemStackData(Material material, int amount) {
        super(material, amount);
    }

    @Override
    public void buildStrings() {
    }
}
