package de.hyper.inventory.old.buttons;

import de.hyper.inventory.old.Inventory;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

@AllArgsConstructor
public class OpenInventoryButton extends Button {

    protected Inventory inventory;
    protected Player player;

    @Override
    public void onClick(InventoryAction inventoryAction) {
        this.inventory.open(player).fillInventory();
    }
}
