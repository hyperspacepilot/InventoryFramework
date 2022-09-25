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
    protected long timeLeft;

    public void registerSlot(int slot, ItemStackData itemStackData, Long timeToWait) {
        animationData.add(new Tuple<>(slot, itemStackData, timeToWait));
    }

    protected void calculateTimeLeft(int position) {
        long left = 0L;
        for (int i = position; position < animationData.size(); position++) {
            left += this.animationData.get(position + 1).getThird();
        }
        this.timeLeft = left;
    }

    public void clear() {
        this.animationData.clear();
    }
}