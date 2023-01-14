package de.hyper.inventory;

import de.hyper.inventory.items.ItemData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyperspace_pilot
 */
@Getter
@NoArgsConstructor
public class InventoryAnimation {

    protected List<Tuple<Integer, ItemData, Long>> animationData = new ArrayList<>();
    @Setter
    protected boolean animating;

    public void registerSlot(int slot, ItemData itemData, Long timeToWait) {
        animationData.add(new Tuple<>(slot, itemData, timeToWait));
    }

    public void clear() {
        this.animationData.clear();
    }
}