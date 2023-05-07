package de.hyper.inventory;

import de.hyper.inventory.buttons.Button;
import de.hyper.inventory.designs.TopBottomLineBackGroundDesign;
import de.hyper.inventory.items.GlassPane;
import de.hyper.inventory.items.SimpleItemData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

/**
 * @author hyperspace_pilot
 */
public class TestInventory extends Inventory {

    public TestInventory(Player player) {
        super(player, "Test-Inventory", 5, true);
        this.setDesign(new TopBottomLineBackGroundDesign(this.getRows(), null, GlassPane.C7));
    }

    @Override
    public Inventory fillInventory() {
        registerItem(2, 2, new SimpleItemData(Material.ARROW).setAmount(3), 1000L);
        registerButtonAndItem(2, 4, new Button() {
            @Override
            public void onClick(InventoryAction inventoryAction) {
                player.sendMessage(inventoryAction.name());
            }
        }, new SimpleItemData(Material.APPLE).setAmount(5), 3000L);
        return this;
    }

    @Override
    public Inventory clearInventory() {
        return this;
    }

    @Override
    public void onOpen() {
    }

    @Override
    public void onClose() {
    }
}