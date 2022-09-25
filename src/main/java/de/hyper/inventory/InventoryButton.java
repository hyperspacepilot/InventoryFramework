package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

@Getter
public abstract class InventoryButton {

    @Setter
    protected boolean cancelClick;
    protected Player player;

    public void click(Player player, InventoryAction inventoryAction) {
        this.player = player;
        onClick(player, inventoryAction);
    }

    public abstract void onAdd();

    public abstract void onClick(Player player, InventoryAction inventoryAction);
}
