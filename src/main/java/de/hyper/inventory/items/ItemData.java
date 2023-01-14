package de.hyper.inventory.items;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyperspace_pilot
 */
@Getter
@RequiredArgsConstructor
public abstract class ItemData {

    private final Material material;
    private Map<Enchantment, Integer> enchantmentsAndLevels = new HashMap<>();
    private int count = 1;
    private ItemFlag[] itemFlags = new ItemFlag[]{};
    private boolean unbreakable = false;
    private int damage = 0;
    protected String displayName = "";
    protected String[] lore = new String[] {""};

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, count);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        itemMeta.setDisplayName(displayName);
        itemMeta.setUnbreakable(unbreakable);
        itemMeta.addItemFlags(itemFlags);
        for (Enchantment enchantment : enchantmentsAndLevels.keySet()) {
            itemMeta.addEnchant(enchantment, enchantmentsAndLevels.get(enchantment), true);
        }
        if (itemMeta instanceof Damageable) {
            Damageable damageable = (Damageable) itemMeta;
            damageable.setDamage(damage);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }public ItemData setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    public ItemData setAmount(int amount) {
        this.count = count;
        return this;
    }

    public ItemData setItemFlags(ItemFlag... itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemData setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemData setEnchantment(Enchantment enchantment, int level) {
        if (enchantmentsAndLevels == null) enchantmentsAndLevels = new HashMap<>();
        enchantmentsAndLevels.put(enchantment, level);
        return this;
    }

    public ItemData setEnchantments(Map<Enchantment, Integer> enchantmentsAndLevels) {
        this.enchantmentsAndLevels = enchantmentsAndLevels;
        return this;
    }

    public ItemData setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public ItemData(Material material, int count, String displayName) {
        this.material = material;
        this.count = count;
        this.displayName = displayName;
    }

    public ItemData(Material material, int count) {
        this.material = material;
        this.count = count;
    }

    public ItemData(Material material, String displayName) {
        this.material = material;
        this.displayName = displayName;
    }

    public ItemData(Material material, Map<Enchantment, Integer> enchantmentsAndLevels, int count, ItemFlag[] itemFlags, boolean unbreakable, int damage, String displayName, String[] lore) {
        this.material = material;
        this.enchantmentsAndLevels = enchantmentsAndLevels;
        this.count = count;
        this.itemFlags = itemFlags;
        this.unbreakable = unbreakable;
        this.damage = damage;
        this.displayName = displayName;
        this.lore = lore;
    }

    public abstract boolean transformStrings();
}
