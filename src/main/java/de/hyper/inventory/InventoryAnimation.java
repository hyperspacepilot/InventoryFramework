package de.hyper.inventory;

import de.hyper.inventory.items.ItemData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hyperspace_pilot
 */
@Getter
@NoArgsConstructor
public class InventoryAnimation {

    protected List<Tuple<Integer, ItemData, Long>> animationData = new CopyOnWriteArrayList<>();
    @Setter
    protected boolean animating;

    public void registerSlot(int slot, ItemData itemData, Long timeToWait) {
        for (Tuple<Integer, ItemData, Long> tuple : animationData) {
            if (tuple.getFirst() == slot) {
                animationData.remove(tuple);
            }
        }
        animationData.add(new Tuple<>(slot, itemData, timeToWait));
    }

    public void clear() {
        this.animationData.clear();
    }
}