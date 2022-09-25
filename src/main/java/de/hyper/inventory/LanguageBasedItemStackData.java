package de.hyper.inventory;

import org.bukkit.entity.Player;

public abstract class LanguageBasedItemStackData extends ItemStackData {

    protected Player player;

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
