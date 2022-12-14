package de.hyper.inventory;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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
        String rawTitle = "§0" + inventory.getTitle();
        this.inventory.setRawTitle(rawTitle);
        this.bukkitInventory = Bukkit.createInventory(
                null, inventory.getRows() * 9, rawTitle);
        this.inventory.setCreated(true);
        this.player.closeInventory();
        this.player.openInventory(this.bukkitInventory);
        this.inventory.onOpen();
        if (!this.inventoryManager.getPlayerInventories().containsKey(player)) {
            this.inventoryManager.getPlayerInventories().put(player, new ArrayList<>(Arrays.asList(this.inventory)));
        } else {
            this.inventoryManager.getPlayerInventories().get(player).add(this.inventory);
        }
        buildDesign();
        buildAnimation();
    }

    protected InventoryBuilder buildDesign() {
        if (inventory.getDesign() != null) {
            InventoryDesign design = inventory.getDesign();
            design.registerItems();
            ItemStackData[][] lines = design.getItems();
            for (int i = 0; i < design.getRows(); i++) {
                ItemStackData[] line = lines[i];
                if (line != null) {
                    for (int a = 0; a < 9; a++) {
                        if (a + 1 <= line.length) {
                            if (bukkitInventory.getItem((i * 9) + a) == null) {
                                ItemStackData itemStackData = line[a];
                                if (itemStackData != null) {
                                    bukkitInventory.setItem((i * 9) + a, itemStackData.build());
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
                Tuple<Integer, ItemStackData, Long> data = this.inventory.animation.animationData.get(i);
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
