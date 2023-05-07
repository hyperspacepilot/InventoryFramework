package de.hyper.inventory.items.old;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyperspace_pilot
 */
public abstract class LanguagedSkullItemData extends SkullItemData {

    public LanguagedSkullItemData(String skullData) {
        super(skullData);
    }

    @Override
    public boolean transformStrings() {
        boolean success = false;
        try {
            this.displayName.set(getText(getDisplayName()));
            List<String> list = new ArrayList<>();
            List<String> result = new ArrayList<>();
            for (String s : this.lore.getValue()) {
                list.add(getText(s));
            }
            for (String s : list) {
                for (String a : s.split("/n/")) {
                    result.add(a);
                }
            }
            this.lore.set(result);
            success = true;
        } catch (Exception e) {}
        return success;
    }

    public abstract String getText(String key);
}
