package de.hyper.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hyperspace_pilot
 * @param <T> First Type
 * @param <U> Second Type
 * @param <V> Third Type
 */
@Getter
@AllArgsConstructor
public class Tuple<T, U, V> {
    private T first;
    private U second;
    private V third;
}
