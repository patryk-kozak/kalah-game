package com.softwaremind.pakz.kalah.model;

import com.softwaremind.pakz.kalah.BoardFixture;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void shouldMarkBoardAsFinished() {
        Board board = new Board(Collections.emptyList());

        board.finish();

        assertThat(board.getStatus()).isEqualTo(BoardStatus.FINISHED);
    }

    @Test
    void shouldSumUpAllPitsForEachPlayerWhenFinished() {
        Board board = BoardFixture.emptyPitsForNorth();

        board.sumUpScore();

        assertThat(board.getPits()).extracting(Pit::getValue)
                .containsExactly(0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 54);
    }

    @Test
    void shouldNotSumUpWhenOngoing() {
        Board board = BoardFixture.initBoard();

        board.sumUpScore();

        assertThat(board.getPits()).extracting(Pit::getValue)
                .containsExactly(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
    }
}
