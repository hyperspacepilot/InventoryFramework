package de.hyper.inventory.items;

import org.bukkit.Material;

/**
 * @author hyperspace_pilot
 */
public class GlassPane {

    public static final ItemData C0 = get("WHITE_STAINED_GLASS_PANE", 0),
            C1 = get("ORANGE_STAINED_GLASS_PANE", 1),
            C2 = get("MAGENTA_STAINED_GLASS_PANE", 2),
            C3 = get("LIGHT_BLUE_STAINED_GLASS_PANE", 3),
            C4 = get("YELLOW_STAINED_GLASS_PANE", 4),
            C5 = get("LIME_STAINED_GLASS_PANE", 5),
            C6 = get("PINK_STAINED_GLASS_PANE", 6),
            C7 = get("GRAY_STAINED_GLASS_PANE", 7),
            C8 = get("LIGHT_GRAY_STAINED_GLASS_PANE", 8),
            C9 = get("CYAN_STAINED_GLASS_PANE", 9),
            C10 = get("PURPLE_STAINED_GLASS_PANE", 10),
            C11 = get("BLUE_STAINED_GLASS_PANE", 11),
            C12 = get("BROWN_STAINED_GLASS_PANE", 12),
            C13 = get("GREEN_STAINED_GLASS_PANE", 13),
            C14 = get("RED_STAINED_GLASS_PANE", 14),
            C15 = get("BLACK_STAINED_GLASS_PANE", 15);
    public static final ItemData WHITE = C0,
            ORANGE = C1,
            MAGENTA = C2,
            LIGHT_BLUE = C3,
            YELLOW = C4,
            LIME = C5,
            PINK = C6,
            GRAY = C7,
            LIGHT_GRAY = C8,
            CYAN = C9,
            PURPLE = C10,
            BLUE = C11,
            BROWN = C12,
            GREEN = C13,
            RED = C14,
            BLACK = C15;

    private static ItemData get(String materialName_1_18, int data) {
        Material material;
        try {
            material = Material.valueOf(materialName_1_18);
        } catch (Exception e) {
            material = Material.valueOf("STAINED_GLASS_PANE");
        }
        ItemData itemData = new SimpleItemData(material);
        itemData.setDataID((byte) data);
        itemData.setDisplayName("§a");
        return itemData;
    }
}
