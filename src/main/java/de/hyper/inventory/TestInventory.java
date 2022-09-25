package de.hyper.inventory;

import de.hyper.inventory.designs.CorneredBackGroundDesign;
import org.bukkit.entity.Player;

public class TestInventory extends Inventory {

    public TestInventory(Player player) {
        super(player, "Test-Inventory", 5, true);
        this.setDesign(new CorneredBackGroundDesign(this.getRows(), null, null, null));
    }

    @Override
    public Inventory fillInventory() {

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