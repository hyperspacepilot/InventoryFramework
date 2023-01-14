package de.hyper.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hyperspace_pilot
 * @param <T> First Value
 * @param <U> Second Value
 */
@Getter
@AllArgsConstructor
public class Duple<T, U> {

    private T first;
    private U second;
}