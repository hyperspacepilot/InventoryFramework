package de.hyper.inventory;

import de.hyper.inventory.buttons.Button;
import de.hyper.inventory.designs.TopBottomLineBackGroundDesign;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

public class TestInventory extends Inventory {

    public TestInventory(Player player) {
        super(player, "Test-Inventory", 5, true);
        this.setDesign(new TopBottomLineBackGroundDesign(this.getRows(), null, GlassPane.C7));
    }

    @Override
    public Inventory fillInventory() {
        registerItem(2, 2, new SimpleItemStackData(Material.ARROW, 3), 1000L);
        registerButtonAndItem(2, 4, new Button() {
            @Override
            public void onClick(InventoryAction inventoryAction) {
                player.sendMessage(inventoryAction.name());
            }
        }, new SimpleItemStackData(Material.APPLE, 5), 3000L);
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