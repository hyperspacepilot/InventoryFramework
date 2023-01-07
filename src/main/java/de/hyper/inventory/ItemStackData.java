package de.hyper.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@Getter
@AllArgsConstructor
public abstract class ItemStackData {

    protected String displayName = "";
    protected String[] lore = new String[] {""};
    protected int amount;
    protected Material material;
    protected ItemFlag[] itemFlags = new ItemFlag[0];
    protected boolean unbreakable = false;
    protected Map<Enchantment, Integer> enchantmentsAndLevels = new HashMap<>();
    protected int damage = 0;

    public ItemStackData(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
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

    public ItemStackData setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemStackData setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackData setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStackData setItemFalgs(ItemFlag... itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemStackData setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemStackData setEnchantment(Enchantment enchantment, int level) {
        if (enchantmentsAndLevels == null) enchantmentsAndLevels = new HashMap<>();
        enchantmentsAndLevels.put(enchantment, level);
        return this;
    }

    public ItemStackData setEnchantments(Map<Enchantment, Integer> enchantmentsAndLevels) {
        this.enchantmentsAndLevels = enchantmentsAndLevels;
        return this;
    }

    public ItemStackData setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public abstract void buildStrings();
}