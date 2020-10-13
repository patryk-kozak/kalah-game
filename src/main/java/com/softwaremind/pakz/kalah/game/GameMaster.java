package com.softwaremind.pakz.kalah.game;

import com.softwaremind.pakz.kalah.IllegalMoveException;
import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import com.softwaremind.pakz.kalah.model.PitType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GameMaster {

    private final Board board;

    public GameMaster(Board board) {
        this.board = board;
    }

    public void validateMove(int pitId) throws IllegalMoveException {
        Pit pit = this.board.getPits().get(pitId - 1);
        if (pit.getType().equals(PitType.STORE)) {
            throw new IllegalMoveException("Can't make move from player store!");
        }

        int pitValue = pit.getValue();
        if (pitValue == 0) {
            throw new IllegalMoveException("This pit is empty, can't make a move.");
        }
    }

    public Board move(int pitId) {
        List<Pit> pits = this.board.getPits();
        Pit pit = this.board.getPits().get(pitId - 1);
        int value = pit.getValue();
        pit.clear();

        int i = 0;
        int index = pitId;
        int indexI = 0;
        while (i < value) {
            if (index + indexI == 14) {
                index = 0;
                indexI = 0;
            }
            pits.get(index + indexI).add();
            i++;
            indexI++;
        }
        return this.board;
    }

    public boolean isEitherHouseSideIsEmpty() {
        Map<Boolean, List<Pit>> sides = this.board.getPits().stream()
                .filter(p -> p.getType().equals(PitType.HOUSE))
                .collect(Collectors.partitioningBy(p -> p.getNumber() < 7));

        return sides.get(true).stream().allMatch(p -> p.getValue() == 0)
                || sides.get(false).stream().allMatch(p -> p.getValue() == 0);
    }
}
