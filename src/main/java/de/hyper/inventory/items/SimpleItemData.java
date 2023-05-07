package de.hyper.inventory.items;

import org.bukkit.Material;

/**
 * @author hyperspace_pilot
 */
public class SimpleItemData extends LanguageItemData{

    public SimpleItemData(Material material) {
        super(material);
    }

    @Override
    public String translate(String languageKey, Object... languagePlaceholderValues) {
        return languageKey;
    }
}
