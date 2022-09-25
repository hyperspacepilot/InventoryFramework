package de.hyper.inventory;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

@RequiredArgsConstructor
public class InventoryBuilder {

    protected final InventoryManager inventoryManager;
    protected Inventory inventory;
    protected Player player;
    protected org.bukkit.inventory.Inventory bukkitInventory;

    public void buildInventory(Inventory inventory) {
        this.inventory = inventory;
        this.inventory.fillInventory();
        this.inventory.setInventoryBuilder(this);
        this.player = inventory.getPlayer();
        this.bukkitInventory = Bukkit.createInventory(
                null, inventory.getRows() * 9, "§0" + inventory.getTitle());
        this.inventory.setCreated(true);
        this.player.closeInventory();
        this.player.openInventory(this.bukkitInventory);
        this.inventory.onOpen();
        if (!this.inventoryManager.getPlayerInventories().containsKey(player)) {
            this.inventoryManager.getPlayerInventories().put(player, Arrays.asList(this.inventory));
        } else {
            this.inventoryManager.getPlayerInventories().get(player).add(this.inventory);
        }
        buildDesign();
        buildAnimation();
    }

    protected void buildDesign() {
        if (inventory.getDesign() != null) {

        }
    }

    protected void buildAnimation() {
        this.inventoryManager.aSync(() -> {
            if (inventory.isAnimated()) {
                this.inventory.animation.setAnimating(true);
            }
            for (int i = 0; i < this.inventory.animation.animationData.size(); i++) {
                Tuple<Integer, ItemStackData, Long> data = this.inventory.animation.animationData.get(i);
                if (inventory.isAnimated() && data.getThird() > 0L) {
                    try {
                        Thread.sleep(data.getThird());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.inventory.animation.calculateTimeLeft(i);
                }
                this.bukkitInventory.setItem(data.getFirst(), data.getSecond().build());
            }
            this.inventory.animation.setAnimating(false);
        });
    }

    public void executeUpdate() {
        this.inventory.clearInventory();
        this.inventory.fillInventory();
        buildAnimation();
    }
}
