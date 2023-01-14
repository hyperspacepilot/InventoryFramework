package de.hyper.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

/**
 * @author hyperspace_pilot
 */
@Getter
public abstract class InventoryButton {

    @Setter
    protected boolean cancelClick = true;
    protected Player player;

    public void click(Player player, InventoryAction inventoryAction) {
        this.player = player;
        onClick(inventoryAction);
    }

    public abstract void onAdd();

    public abstract void onClick(InventoryAction inventoryAction);
}
