package de.hyper.inventory.old.buttons;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public abstract class MultiButton<T> extends Button {

    protected Map<T, Button> buttons;

}