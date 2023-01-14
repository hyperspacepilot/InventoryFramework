package de.hyper.inventory.buttons;
import de.hyper.inventory.Inventory;
import de.hyper.inventory.InventoryFramework;
import de.hyper.inventory.InventoryManager;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

/**
 * @author hyperspace_pilot
 */
@AllArgsConstructor
public class OpenInventoryButton extends Button {

    protected InventoryManager inventoryManager;
    protected Inventory inventory;
    protected Player player;

    @Override
    public void onClick(InventoryAction inventoryAction) {
        InventoryFramework.createInventoryBuilder(this.inventoryManager).buildInventory(inventory);
    }
}