package de.hyper.inventory.items.old;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @param <T>
 * @author hyperspace_pilot
 */
@AllArgsConstructor
@Getter
public class ItemDataAttribute<T> {
    private final String key;
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public void modify(ItemDataModifier<T> modifier) {
        this.value = modifier.modify(this);
    }
}