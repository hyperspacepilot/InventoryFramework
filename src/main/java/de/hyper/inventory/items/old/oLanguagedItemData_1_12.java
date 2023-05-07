package de.hyper.inventory.items.old;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hyperspace_pilot
 */
public abstract class oLanguagedItemData_1_12 extends oItemData_1_12 {
    public oLanguagedItemData_1_12(Material material) {
        super(material);
    }

    @Override
    public boolean transformStrings() {
        try {
            this.displayName = getText(displayName);
            List<String> list = new ArrayList<>();
            for (String s : this.lore) {
                Collections.addAll(list, getText(s).split("/n/"));
            }
            lore = new String[list.size()];
            int i = 0;
            for (String s : list) {
                lore[i++] = s;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public abstract String getText(String key);
}