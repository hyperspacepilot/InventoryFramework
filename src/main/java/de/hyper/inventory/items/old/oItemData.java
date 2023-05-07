package de.hyper.inventory.items.old;

import de.hyper.inventory.InventoryFramework;
import de.hyper.inventory.ServerVersion;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * @author hyperspace_pilot
 */
public interface oItemData {

    ItemStack build();

    oItemData setDisplayName(String displayName);

    oItemData setLore(String... lore);

    oItemData setAmount(int amount);

    oItemData setItemFlags(ItemFlag... itemFlags);

    oItemData setUnbreakable(boolean unbreakable);

    oItemData setEnchantment(Enchantment enchantment, int level);

    oItemData setEnchantments(Map<Enchantment, Integer> enchantmentsAndLevels);

    oItemData setDamage(int damage);

    boolean transformStrings();

    static oItemData simple(Material material) {
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_18)) {
            return new oSimpleItemData_1_18(material);
        } else {
            return new oSimpleItemData_1_12(material);
        }
    }
}
