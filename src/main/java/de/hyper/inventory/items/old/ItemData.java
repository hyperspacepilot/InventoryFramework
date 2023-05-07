package de.hyper.inventory.items.old;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hyperspace_pilot
 */
public abstract class ItemData {

    protected ItemDataAttribute<Material> material = new ItemDataAttribute<>("material", null);
    protected ItemDataAttribute<Byte> dataid = new ItemDataAttribute<>("dataid", (byte) -1);
    protected ItemDataAttribute<String> displayName = new ItemDataAttribute<>("displayname", null);
    protected ItemDataAttribute<List<String>> lore = new ItemDataAttribute<>("lore", new ArrayList<>());
    protected ItemDataAttribute<Integer> amount = new ItemDataAttribute<>("amount", 1);
    protected ItemDataAttribute<List<ItemFlag>> itemFlags = new ItemDataAttribute<>("itemflags", new ArrayList<>());
    protected ItemDataAttribute<Boolean> unbreakable = new ItemDataAttribute<>("unbreakable", false);
    protected ItemDataAttribute<Map<Enchantment, Integer>> enchantments = new ItemDataAttribute<>("enchantments", new HashMap<>());
    protected ItemDataAttribute<Integer> damage = new ItemDataAttribute<>("damage", 0);
    protected List<ItemDataModifier> modifiers = new ArrayList<>();
    protected List<ItemDataAttribute> attributes = new ArrayList<>();

    public ItemData(Material material) {
        this.material.set(material);
        this.attributes.add(this.material);
        this.attributes.add(this.dataid);
        this.attributes.add(this.displayName);
        this.attributes.add(this.lore);
        this.attributes.add(this.amount);
        this.attributes.add(this.itemFlags);
        this.attributes.add(this.unbreakable);
        this.attributes.add(this.enchantments);
        this.attributes.add(this.damage);
    }

    public Material getMaterial() {
        return material.getValue();
    }

    public ItemData setDataID(byte dataID) {
        this.dataid.set(dataID);
        return this;
    }

    public byte getDataID() {
        return this.dataid.getValue();
    }

    public ItemData setDisplayName(String displayName) {
        this.displayName.set(displayName);
        return this;
    }

    public String getDisplayName() {
        return displayName.getValue();
    }

    public ItemData setLore(String... strings) {
        this.lore.set(Arrays.stream(strings).collect(Collectors.toList()));
        return this;
    }
    public ItemData addLore(String... strings) {
        this.lore.modify(new ItemDataModifier<>(this.lore.getKey()) {
            @Override
            public List<String> modify(ItemDataAttribute<List<String>> dataAttribute) {
                for (String s : strings) {
                    dataAttribute.getValue().add(s);
                }
                return dataAttribute.getValue();
            }
        });
        return this;
    }

    public List<String> getLore() {
        return lore.getValue();
    }

    public ItemData setAmount(int amount) {
        this.amount.set(amount);
        return this;
    }

    public int getAmount() {
        return amount.getValue();
    }

    public ItemData addItemFlags(ItemFlag... itemFlags) {
        this.itemFlags.modify(new ItemDataModifier<>(this.itemFlags.getKey()) {
            @Override
            public List<ItemFlag> modify(ItemDataAttribute<List<ItemFlag>> dataAttribute) {
                for (ItemFlag flag : itemFlags) {
                    dataAttribute.getValue().add(flag);
                }
                return dataAttribute.getValue();
            }
        });
        return this;
    }

    public List<ItemFlag> getItemFlags() {
        return itemFlags.getValue();
    }

    public ItemData setUnbreakable(boolean unbreakable) {
        this.unbreakable.set(unbreakable);
        return this;
    }

    public boolean isUnbreakable() {
        return unbreakable.getValue();
    }

    public ItemData setEnchantment(Enchantment enchantment, int level) {
        this.enchantments.modify(new ItemDataModifier<>(this.enchantments.getKey()) {
            @Override
            public Map<Enchantment, Integer> modify(ItemDataAttribute<Map<Enchantment, Integer>> dataAttribute) {
                dataAttribute.getValue().put(enchantment, level);
                return dataAttribute.getValue();
            }
        });
        return this;
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments.getValue();
    }

    public ItemData setDamage(int damage) {
        this.damage.set(damage);
        return this;
    }

    public int getDamage() {
        return damage.getValue();
    }

    public void addModifier(ItemDataModifier modifier) {
        this.modifiers.add(modifier);
    }

    public abstract boolean transformStrings();

    public ItemStack build() {
        return ItemDataBuilder.simpleBuilder(this);
    }
}