package de.hyper.inventory.items.old;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Damageable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyperspace_pilot
 */
@Getter
@RequiredArgsConstructor
public abstract class oItemData_1_18 implements oItemData {

    private final Material material;
    protected String displayName = "";
    protected String[] lore = new String[]{""};
    private Map<Enchantment, Integer> enchantmentsAndLevels = new HashMap<>();
    private int count = 1;
    private ItemFlag[] itemFlags = new ItemFlag[]{};
    private boolean unbreakable = false;
    private int damage = 0;

    @Override
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
        if (itemMeta instanceof Damageable damageable) {
            damageable.setDamage(damage);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public oItemData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public oItemData setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    @Override
    public oItemData setAmount(int amount) {
        this.count = amount;
        return this;
    }

    @Override
    public oItemData setItemFlags(ItemFlag... itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    @Override
    public oItemData setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    @Override
    public oItemData setEnchantment(Enchantment enchantment, int level) {
        if (enchantmentsAndLevels == null) enchantmentsAndLevels = new HashMap<>();
        enchantmentsAndLevels.put(enchantment, level);
        return this;
    }

    @Override
    public oItemData setEnchantments(Map<Enchantment, Integer> enchantmentsAndLevels) {
        this.enchantmentsAndLevels = enchantmentsAndLevels;
        return this;
    }

    @Override
    public oItemData setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public abstract boolean transformStrings();
}