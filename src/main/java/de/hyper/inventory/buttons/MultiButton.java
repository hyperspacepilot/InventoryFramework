package de.hyper.inventory.buttons;

import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author hyperspace_pilot
 * @param <T> Identifier-Type to get the correct button.
 */
@AllArgsConstructor
public abstract class MultiButton<T> extends Button {

    protected Map<T, Button> buttons;

}