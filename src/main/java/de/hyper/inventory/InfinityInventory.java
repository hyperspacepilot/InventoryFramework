package de.hyper.inventory;

import de.hyper.inventory.buttons.Button;
import de.hyper.inventory.items.ItemData;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;

import java.util.List;

/**
 * @author hyperspace_pilot
 * @param <T> Type of Objects in InfinityInventory
 */
@Getter
@Setter
public abstract class InfinityInventory<T> extends Inventory {

    protected List<T> list;
    protected int currentPage;
    protected double maxPage;

    public InfinityInventory(Player player, String title, int rows) {
        super(player, title, rows);
    }

    public void registerNextPageButton(int row, int slot, ItemData itemData) {
        registerButtonAndItem(row, slot, new Button() {
            @Override
            public void onClick(InventoryAction inventoryAction) {
                currentPage++;
                inventoryBuilder.executeUpdate();
            }
        }, itemData);
    }

    public void registerLastPageButton(int row, int slot, ItemData itemData) {
        registerButtonAndItem(row, slot, new Button() {
            @Override
            public void onClick(InventoryAction inventoryAction) {
                currentPage--;
                inventoryBuilder.executeUpdate();
            }
        }, itemData);
    }
}