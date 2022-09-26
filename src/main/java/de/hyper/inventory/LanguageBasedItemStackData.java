package de.hyper.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

public abstract class LanguageBasedItemStackData extends ItemStackData {

    protected Player player;

    public LanguageBasedItemStackData(String displayName, String[] lore, int amount, Material material, ItemFlag[] itemFlags, boolean unbreakable, List<Enchantment> enchantments, int damage) {
        super(displayName, lore, amount, material, itemFlags, unbreakable, enchantments, damage);
    }

    public LanguageBasedItemStackData(Material material, int amount) {
        super(material, amount);
    }

    public void build(Player player) {
        this.player = player;
        buildStrings();
    }

    @Override
    public void buildStrings() {
        this.displayName = getLanguageTextFromKey(this.displayName);
        String[] newLore = new String[this.lore.length];
        for (int i = 0; i < this.lore.length; i++) {
            newLore[i] = getLanguageTextFromKey(this.lore[i]);
        }
        this.lore = newLore;
    }

    protected abstract String getLanguageTextFromKey(String key);
}
