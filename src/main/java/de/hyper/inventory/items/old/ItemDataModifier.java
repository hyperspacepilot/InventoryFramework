package de.hyper.inventory.items.old;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hyperspace_pilot
 * @param <T>
 */
@AllArgsConstructor @Getter
public abstract class ItemDataModifier<T> {

    private final String targetKey;

    public abstract T modify(ItemDataAttribute<T> dataAttribute);
}
