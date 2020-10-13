package com.softwaremind.pakz.kalah.model;

import lombok.Getter;

/**
 * Pit is single unit in Kalah game
 * They come in two types, have unique number and holds value
 */
@Getter
public class Pit {
    private final int number;
    private final PitType type;
    private int value;

    public Pit(int number, PitType type) {
        this.number = number;
        this.type = type;
        this.value = 6;
    }

    public void add() {
        ++this.value;
    }

    public void clear() {
        this.value = 0;
    }

    public void addValue(int value) {
        if (this.getType().equals(PitType.STORE)) {
            this.value += value;
        }
    }
}
