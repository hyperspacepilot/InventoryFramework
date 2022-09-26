package de.hyper.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class ItemStackData {

    protected String displayName;
    protected String[] lore;
    protected int amount;
    protected Material material;
    protected ItemFlag[] itemFlags;
    protected boolean unbreakable;
    protected List<Enchantment> enchantments;
    protected int damage;

    public ItemStackData(Material material, int amount) {
        this.material = material;
        this.amount = amount;
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

    public ItemStackData setEnchantments(Enchantment... enchantments) {
        this.enchantments = new ArrayList<>(Arrays.asList(enchantments));
        return this;
    }

    public ItemStackData setEnchantments(List<Enchantment> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemStackData setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount);
        return itemStack;
    }

    public abstract void buildStrings();
}