package com.softwaremind.pakz.kalah.model;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Board that represents current game of Kalah
 * <p>
 * Pits <1-6> are North player houses
 * Pit 7 is North player store
 * Pits <8-13> are South player houses
 * Pit 14 is South player store
 */
@Getter
public class Board {
    private final List<Pit> pits;
    private BoardStatus status;

    public Board(List<Pit> pits) {
        this.pits = pits;
        this.status = BoardStatus.ONGOING;
    }

    public void finish() {
        this.status = BoardStatus.FINISHED;
    }

    public boolean isFinished() {
        return this.status.equals(BoardStatus.FINISHED);
    }

    public void sumUpScore() {
        if (this.isFinished()) {
            this.southStore().addValue(this.southHouses().stream().mapToInt(Pit::getValue).sum());
            this.northStore().addValue(this.northHouses().stream().mapToInt(Pit::getValue).sum());
            this.clearAllHouses();
        }
    }

    private Pit northStore() {
        return this.pits.get(6);
    }

    private Pit southStore() {
        return this.pits.get(13);
    }

    private List<Pit> southHouses() {
        return this.pits.stream()
                .filter(p -> p.getType().equals(PitType.HOUSE))
                .collect(Collectors.partitioningBy(p -> p.getNumber() < 7)).get(false);
    }

    private List<Pit> northHouses() {
        return this.pits.stream()
                .filter(p -> p.getType().equals(PitType.HOUSE))
                .collect(Collectors.partitioningBy(p -> p.getNumber() < 7)).get(true);
    }

    private void clearAllHouses() {
        this.pits.stream()
                .filter(p -> p.getType().equals(PitType.HOUSE))
                .forEach(Pit::clear);
    }
}
