package de.hyper.inventory.items.old;

import de.hyper.inventory.InventoryFramework;
import de.hyper.inventory.ServerVersion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author hyperspace_pilot
 */
public abstract class SkullItemData extends ItemData {

    private ItemDataAttribute<String> skullData = new ItemDataAttribute<>("skulldata", null);

    public SkullItemData(String skullData) {
        super(getSkullMaterialByVersion());
        this.skullData.set(skullData);
        this.attributes.add(this.skullData);
    }

    public String getSkullData() {
        return skullData.getValue();
    }

    @Override
    public ItemStack build() {
        return ItemDataBuilder.skullBuilder(this);
    }

    private static Material getSkullMaterialByVersion() {
        ServerVersion currentVersion = InventoryFramework.get().getServerVersion();
        if (currentVersion.is(ServerVersion.V1_18) || currentVersion.isHigherThen(ServerVersion.V1_19)) {
            return Material.getMaterial("PLAYER_HEAD");
        } else {
            return Material.getMaterial("SKULL_ITEM");
        }
    }
}
