package de.hyper.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tuple<T, U, V> {
    private T first;
    private U second;
    private V third;
}
