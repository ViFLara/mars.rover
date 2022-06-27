package com.elo7.mars.rover.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {

    N('N'),

    E('E'),

    W('W'),

    S('S');

    private final char n;
}
