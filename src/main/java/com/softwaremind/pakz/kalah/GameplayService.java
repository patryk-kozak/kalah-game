package com.softwaremind.pakz.kalah;

import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import com.softwaremind.pakz.kalah.model.PitType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameplayService {

    private final BoardProvider boardProvider;

    public Board makeMove(long id, int pitId) throws IllegalMoveException {
        Board board = boardProvider.retrieveBoard(id);
        List<Pit> pits = board.getPits();

        Pit pit = pits.get(pitId - 1);
        if (pit.getType().equals(PitType.STORE)) {
            throw new IllegalMoveException("Can't make move from player store!");
        }

        int pitValue = pit.getValue();
        if (pitValue == 0) {
            throw new IllegalMoveException("This pit is empty, can't make a move.");
        }

        int i = 0;
        pit.clear();
        int index = pitId;
        int indexI = 0;
        while (i < pitValue) {
            if (index + indexI == 14) {
                index = 0;
                indexI = 0;
            }
            pits.get(index + indexI).add();
            i++;
            indexI++;
        }

        if (eitherHouseSideIsEmpty(pits)) {
            board.finish();
            board.sumUpScore();
        }

        return board;
    }

    private boolean eitherHouseSideIsEmpty(List<Pit> pits) {
        Map<Boolean, List<Pit>> sides = pits.stream()
                .filter(p -> p.getType().equals(PitType.HOUSE))
                .collect(Collectors.partitioningBy(p -> p.getNumber() < 7));

        return sides.get(true).stream().allMatch(p -> p.getValue() == 0)
                || sides.get(false).stream().allMatch(p -> p.getValue() == 0);
    }

}
