package de.hyper.inventory.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.hyper.inventory.Duple;
import de.hyper.inventory.InventoryFramework;
import de.hyper.inventory.ServerVersion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author hyperspace_pilot
 */
public abstract class LanguageItemData implements ItemData {

    private final List<Duple<String, List<Object>>> lore = new ArrayList<>();
    private final List<ItemFlag> itemFlags = new ArrayList<>();
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private Duple<String, List<Object>> displayName;
    private final Material material;
    private byte dataID = 0;
    private short damage = 0;
    private boolean unbreakable = false;
    private int amount = 1;
    private String skullOwner;
    private String skullValue;

    public LanguageItemData(Material material) {
        this.material = material;
    }

    @Override
    public ItemData setDisplayName(String displayName, Object... languagePlaceholderValues) {
        List<Object> list = new ArrayList<>();
        Arrays.stream(languagePlaceholderValues).forEach(languagePlaceholderValue -> list.add(languagePlaceholderValue));
        this.displayName = new Duple<>(displayName, list);
        return this;
    }

    @Override
    public ItemData addLore(String lore, Object... languagePlaceholderValues) {
        List<Object> list = new ArrayList<>();
        Arrays.stream(languagePlaceholderValues).forEach(languagePlaceholderValue -> list.add(languagePlaceholderValue));
        this.lore.add(new Duple<>(lore, list));
        return this;
    }

    @Override
    public ItemData addItemFlags(ItemFlag... itemFlags) {
        for (ItemFlag itemFlag : itemFlags) {
            if (!this.itemFlags.contains(itemFlag)) this.itemFlags.add(itemFlag);
        }
        return this;
    }

    @Override
    public ItemData addEnchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    @Override
    public ItemData setSkullOwner(String skullOwner) {
        this.skullOwner = skullOwner;
        return this;
    }

    @Override
    public String getSkullOwner() {
        return skullOwner;
    }

    @Override
    public ItemData setSkullValue(String skullValue) {
        this.skullValue = skullValue;
        return this;
    }

    @Override
    public String getSkullValue() {
        return skullValue;
    }

    @Override
    public ItemStack build() {
        ServerVersion version = InventoryFramework.get().getServerVersion();
        ItemStack itemStack;
        if (version.is(ServerVersion.V1_8) || version.is(ServerVersion.V1_12)) {
            itemStack = new ItemStack(material, amount, damage, dataID);
        } else {
            itemStack = new ItemStack(material, amount, damage);
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (InventoryFramework.get().getServerVersion().isHigherThen(ServerVersion.V1_8)) {
            itemMeta.setUnbreakable(this.unbreakable);
        }
        if (InventoryFramework.get().getServerVersion().is(ServerVersion.V1_8) || InventoryFramework.get().getServerVersion().is(ServerVersion.V1_12)) {
            itemStack.setData(new MaterialData(material, dataID));
        }
        if (InventoryFramework.get().getServerVersion().isHigherThen(ServerVersion.V1_12)) {
            if (itemMeta instanceof Damageable damageable) {
                damageable.setDamage(this.damage);
            }
        }
        if (itemMeta instanceof SkullMeta skullMeta) {
            if (skullOwner != null) {
                skullMeta.setOwnerProfile(Bukkit.createPlayerProfile(skullOwner));
            }
            if (this.skullValue != null) {
                GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
                gameProfile.getProperties().put("textures", new Property("textures", this.skullValue));
                Field profileField;
                try {
                    profileField = skullMeta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                    profileField.set(skullMeta, gameProfile);
                    /*
                    //DEBUG
                    GameProfile profile = (GameProfile) profileField.get(skullMeta);
                    Collection<Property> properties = profile.getProperties().get("textures");
                    for (Property property : properties) {
                        if (property.getValue().equals(skullValue)) {
                            System.out.println("FOUND");
                        }
                    }
                     */
                } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            itemStack.setItemMeta(skullMeta);
        }
        ItemFlag[] flags = new ItemFlag[this.itemFlags.size()];
        int i = 0;
        for (ItemFlag itemFlag : this.itemFlags) {
            flags[i] = itemFlag;
            i++;
        }
        itemMeta.addItemFlags(flags);
        if (this.displayName != null) {
            itemMeta.setDisplayName(translate(this.displayName.getFirst(), this.displayName.getSecond().toArray()));
        }
        List<String> s = new ArrayList<>();
        for (Duple<String, List<Object>> duple : this.lore) {
            String rawText = translate(duple.getFirst(), duple.getSecond().toArray());
            for (String splittedText : rawText.split("/n/")) {
                s.add(splittedText);
            }
        }
        itemMeta.setLore(s);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Material getMaterial() {
        return this.material;
    }

    @Override
    public byte getDataID() {
        return this.dataID;
    }

    @Override
    public ItemData setDataID(byte dataID) {
        this.dataID = dataID;
        return this;
    }

    @Override
    public short getDamage() {
        return this.damage;
    }

    @Override
    public ItemData setDamage(short damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    @Override
    public ItemData setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public ItemData setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String getDisplayName() {
        return this.displayName.getFirst();
    }

    public abstract String translate(String languageKey, Object... languagePlaceholderValues);
}