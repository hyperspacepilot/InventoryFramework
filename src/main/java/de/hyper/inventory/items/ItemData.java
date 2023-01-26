package de.hyper.inventory.items;

import de.hyper.inventory.InventoryFramework;
import de.hyper.inventory.ServerVersion;
import de.hyper.inventory.items.impl.SimpleItemData_1_12;
import de.hyper.inventory.items.impl.SimpleItemData_1_18;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * @author hyperspace_pilot
 */
public interface ItemData {

    ItemStack build();

    ItemData setDisplayName(String displayName);

    ItemData setLore(String... lore);

    ItemData setAmount(int amount);

    ItemData setItemFlags(ItemFlag... itemFlags);

    ItemData setUnbreakable(boolean unbreakable);

    ItemData setEnchantment(Enchantment enchantment, int level);

    ItemData setEnchantments(Map<Enchantment, Integer> enchantmentsAndLevels);

    ItemData setDamage(int damage);

    boolean transformStrings();

    static ItemData simple(Material material) {
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_18)) {
            return new SimpleItemData_1_18(material);
        } else {
            return new SimpleItemData_1_12(material);
        }
    }
}
