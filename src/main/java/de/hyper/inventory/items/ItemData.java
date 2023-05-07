package de.hyper.inventory.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

/**
 * @author hyperspace_pilot
 */
public interface ItemData {

    /**
     *
     * @param displayName = insert raw text or language-key here
     * @param languagePlaceholderValues = optional objects to fill placeholders when using language-system
     * @return this ItemData object
     */
    ItemData setDisplayName(String displayName, Object... languagePlaceholderValues);

    /**
     *
     * @param lore = insert raw text or language-key here
     * @param languagePlaceholderValues = optional objects to fill placeholders when using language-system
     * @return this ItemData object
     */
    ItemData addLore(String lore, Object... languagePlaceholderValues);

    ItemData addItemFlags(ItemFlag... itemFlags);

    ItemData addEnchantment(Enchantment enchantment, int level);

    /**
     * Method to define the texture of a skull. Only works if material is a Skull.
     * Only use skullOwner or skullValue, not both!
     * @param skullOwner = name of player, which texture should be used.
     * @return this ItemData object
     */
    ItemData setSkullOwner(String skullOwner);

    String getSkullOwner();

    /**
     * Method to define the texture of a skull. Only works if material is a Skull.
     * Only use skullOwner or skullValue, not both!
     * @param skullValue = value-base64-string, which represents the texture.
     * @return this ItemData object
     */
    ItemData setSkullValue(String skullValue);

    String getSkullValue();

    ItemStack build();

    Material getMaterial();

    byte getDataID();

    ItemData setDataID(byte dataID);

    short getDamage();

    ItemData setDamage(short damage);

    boolean isUnbreakable();

    ItemData setUnbreakable(boolean unbreakable);

    int getAmount();

    ItemData setAmount(int amount);

    String getDisplayName();
}