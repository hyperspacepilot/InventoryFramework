package de.hyper.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Duple<T, U> {

    private T first;
    private U second;
}