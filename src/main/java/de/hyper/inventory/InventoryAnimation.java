package de.hyper.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class InventoryAnimation {

    protected List<Tuple<Integer, ItemStackData, Long>> animationData = new ArrayList<>();
    @Setter
    protected boolean animating;

    public void registerSlot(int slot, ItemStackData itemStackData, Long timeToWait) {
        animationData.add(new Tuple<>(slot, itemStackData, timeToWait));
    }

    public void clear() {
        this.animationData.clear();
    }
}