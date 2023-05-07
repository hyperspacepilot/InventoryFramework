package de.hyper.inventory.items.old;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyperspace_pilot
 */
@Getter
@RequiredArgsConstructor
public abstract class oItemData_1_12 implements oItemData {

    private final Material material;
    protected String displayName = "";
    protected String[] lore = new String[]{""};
    private Map<Enchantment, Integer> enchantmentsAndLevels = new HashMap<>();
    private int count = 1;
    private ItemFlag[] itemFlags = new ItemFlag[]{};

    private byte data = -1;

    @Override
    public ItemStack build() {
        ItemStack itemStack;
        if (data != -1) {
            itemStack = new ItemStack(material, count, data);
        } else {
            itemStack = new ItemStack(material, count);
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        itemMeta.setDisplayName(displayName);
        itemMeta.addItemFlags(itemFlags);
        for (Enchantment enchantment : enchantmentsAndLevels.keySet()) {
            itemMeta.addEnchant(enchantment, enchantmentsAndLevels.get(enchantment), true);
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
        return this;
    }

    public oItemData_1_12 setData(byte data) {
        this.data = data;
        return this;
    }

    public abstract boolean transformStrings();
}