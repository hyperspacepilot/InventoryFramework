package de.hyper.inventory.items.old;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.hyper.inventory.InventoryFramework;
import de.hyper.inventory.ServerVersion;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * @author hyperspace_pilot
 */
public class ItemDataBuilder {

    public static ItemStack simpleBuilder(ItemData itemData) {
        itemData.transformStrings();
        ItemStack itemStack = new ItemStack(itemData.getMaterial(), itemData.getAmount());
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemData.getLore() != null) {
            itemMeta.setLore(itemData.getLore());
        }
        if (itemData.getDisplayName() != null) {
            itemMeta.setDisplayName(itemData.getDisplayName());
        }
        ItemFlag[] itemFlags = new ItemFlag[itemData.getItemFlags().size()];
        int i = 0;
        for (ItemFlag flag : itemData.getItemFlags()) {
            itemFlags[i] = flag;
            i++;
        }
        itemMeta.addItemFlags(itemFlags);
        for (Enchantment enchantment : itemData.getEnchantments().keySet()) {
            itemMeta.addEnchant(enchantment,  itemData.getEnchantments().get(enchantment), true);
        }
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_12)) {
            itemStack.setData(new MaterialData(itemStack.getType(), itemData.getDataID()));
        }
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_18)) {
            itemMeta.setUnbreakable(itemData.isUnbreakable());
            if (itemMeta instanceof Damageable damageable) {
                damageable.setDamage(itemData.getDamage());
            }
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack skullBuilder(SkullItemData skullItemData) {
        skullItemData.transformStrings();
        ItemStack itemStack = new ItemStack(skullItemData.getMaterial(), skullItemData.getAmount(), (short) 3);
        if (!skullItemData.getSkullData().isEmpty()) {
            SkullMeta headMeta = (SkullMeta) itemStack.getItemMeta();
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "");
            gameProfile.getProperties().put("textures", new Property("textures", skullItemData.getSkullData()));
            Field profileField;
            try {
                profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, gameProfile);
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
            itemStack.setItemMeta(headMeta);
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (skullItemData.getLore() != null) {
            itemMeta.setLore(skullItemData.getLore());
        }
        if (skullItemData.getDisplayName() != null) {
            itemMeta.setDisplayName(skullItemData.getDisplayName());
        }
        ItemFlag[] itemFlags = new ItemFlag[skullItemData.getItemFlags().size()];
        int i = 0;
        for (ItemFlag flag : skullItemData.getItemFlags()) {
            itemFlags[i] = flag;
            i++;
        }
        itemMeta.addItemFlags(itemFlags);
        for (Enchantment enchantment : skullItemData.getEnchantments().keySet()) {
            itemMeta.addEnchant(enchantment,  skullItemData.getEnchantments().get(enchantment), true);
        }
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_12)) {
            itemStack.setData(new MaterialData(itemStack.getType(), skullItemData.getDataID()));
        }
        if (InventoryFramework.get().getServerVersion().equals(ServerVersion.V1_18)) {
            itemMeta.setUnbreakable(skullItemData.isUnbreakable());
            if (itemMeta instanceof Damageable damageable) {
                damageable.setDamage(skullItemData.getDamage());
            }
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}