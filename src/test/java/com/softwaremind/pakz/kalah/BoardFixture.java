package com.softwaremind.pakz.kalah;

import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import com.softwaremind.pakz.kalah.model.PitType;

import java.util.List;

public class BoardFixture {
    public static Board initBoard() {
        List<Pit> pits = PitFixture.initPits();
        return new Board(pits);
    }

    public static Board firstPitEmpty() {
        Board board = BoardFixture.initBoard();
        board.getPits().get(0).clear();
        return board;
    }

    public static Board lastPitForSouth() {
        List<Pit> customPits = List.of(
                PitFixture.customValuePit(1, 0, PitType.HOUSE),
                PitFixture.customValuePit(2, 0, PitType.HOUSE),
                PitFixture.customValuePit(3, 0, PitType.HOUSE),
                PitFixture.customValuePit(4, 0, PitType.HOUSE),
                PitFixture.customValuePit(5, 0, PitType.HOUSE),
                PitFixture.customValuePit(6, 2, PitType.HOUSE),
                PitFixture.customValuePit(7, 17, PitType.STORE),
                PitFixture.customValuePit(8, 0, PitType.HOUSE),
                PitFixture.customValuePit(9, 2, PitType.HOUSE),
                PitFixture.customValuePit(10, 1, PitType.HOUSE),
                PitFixture.customValuePit(11, 8, PitType.HOUSE),
                PitFixture.customValuePit(12, 4, PitType.HOUSE),
                PitFixture.customValuePit(13, 2, PitType.HOUSE),
                PitFixture.customValuePit(14, 36, PitType.STORE)
        );
        return new Board(customPits);
    }

    public static Board emptyPitsForNorth() {
        List<Pit> customPits = List.of(
                PitFixture.customValuePit(1, 0, PitType.HOUSE),
                PitFixture.customValuePit(2, 0, PitType.HOUSE),
                PitFixture.customValuePit(3, 0, PitType.HOUSE),
                PitFixture.customValuePit(4, 0, PitType.HOUSE),
                PitFixture.customValuePit(5, 0, PitType.HOUSE),
                PitFixture.customValuePit(6, 0, PitType.HOUSE),
                PitFixture.customValuePit(7, 18, PitType.STORE),
                PitFixture.customValuePit(8, 1, PitType.HOUSE),
                PitFixture.customValuePit(9, 2, PitType.HOUSE),
                PitFixture.customValuePit(10, 1, PitType.HOUSE),
                PitFixture.customValuePit(11, 8, PitType.HOUSE),
                PitFixture.customValuePit(12, 4, PitType.HOUSE),
                PitFixture.customValuePit(13, 2, PitType.HOUSE),
                PitFixture.customValuePit(14, 36, PitType.STORE)
        );
        Board board = new Board(customPits);
        board.finish();
        return board;
    }
}
