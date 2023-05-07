package de.hyper.inventory;

import de.hyper.inventory.items.ItemData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hyperspace_pilot
 */
@RequiredArgsConstructor
@Getter
public class InventoryBuilder {

    protected final InventoryManager inventoryManager;
    protected Inventory inventory;
    protected Player player;
    protected org.bukkit.inventory.Inventory bukkitInventory;

    public void buildInventory(Inventory inventory) {
        this.inventory = inventory;
        this.inventory.setInventoryBuilder(this);
        String rawTitle = "§0" + inventory.getTitle();
        this.bukkitInventory = Bukkit.createInventory(
                null, inventory.getRows() * 9, rawTitle);
        this.player = inventory.getPlayer();
        this.inventory.setRawTitle(rawTitle);
        this.inventory.fillInventory();
        this.inventory.setCreated(true);
        this.player.closeInventory();
        this.player.openInventory(this.bukkitInventory);
        this.inventory.onOpen();
        if (!this.inventoryManager.getPlayerInventories().containsKey(player)) {
            this.inventoryManager.getPlayerInventories().put(player, new CopyOnWriteArrayList<>());
        }
        this.inventoryManager.getPlayerInventories().get(player).add(this.inventory);
        buildDesign();
        buildAnimation();
    }

    protected InventoryBuilder buildDesign() {
        if (inventory.getDesign() != null) {
            InventoryDesign design = inventory.getDesign();
            design.registerItems();
            ItemData[][] lines = design.getItems();
            for (int i = 0; i < design.getRows(); i++) {
                ItemData[] line = lines[i];
                if (line != null) {
                    for (int a = 0; a < 9; a++) {
                        if (a + 1 <= line.length) {
                            if (bukkitInventory.getItem((i * 9) + a) == null) {
                                ItemData itemData = line[a];
                                if (itemData != null) {
                                    bukkitInventory.setItem((i * 9) + a, itemData.build());
                                }
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    protected void buildAnimation() {
        this.inventoryManager.aSync(() -> {
            if (inventory.isAnimated()) {
                this.inventory.animation.setAnimating(true);
            }
            for (int i = 0; i < this.inventory.animation.animationData.size(); i++) {
                Tuple<Integer, ItemData, Long> data = this.inventory.animation.animationData.get(i);
                if (inventory.isAnimated() && data.getThird() > 0L) {
                    try {
                        Thread.sleep(data.getThird());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.bukkitInventory.setItem(data.getFirst(), data.getSecond().build());
            }
            this.inventory.animation.setAnimating(false);
        });
    }

    public InventoryBuilder executeUpdate() {
        this.inventory.clearInventory();
        this.inventory.fillInventory();
        buildDesign().buildAnimation();
        return this;
    }
}