package de.hyper.inventory;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public abstract class ItemStackData {

    protected String displayName;
    protected String[] lore;
    protected int amount;
    protected Material material;
    protected ItemFlag[] itemFlags;
    protected boolean unbreakable;
    protected List<Enchantment> enchantments;
    protected int damage;

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        return itemStack;
    }

    public abstract void buildStrings();
}