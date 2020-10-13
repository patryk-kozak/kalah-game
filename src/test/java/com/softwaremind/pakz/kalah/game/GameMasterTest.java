package com.softwaremind.pakz.kalah.game;

import com.softwaremind.pakz.kalah.BoardFixture;
import com.softwaremind.pakz.kalah.IllegalMoveException;
import com.softwaremind.pakz.kalah.game.GameMaster;
import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GameMasterTest {

    @Test
    void disallowMoveWhenPitIsStore() {
        assertThatExceptionOfType(IllegalMoveException.class)
                .isThrownBy(() -> new GameMaster(BoardFixture.initBoard()).validateMove(7))
                .withMessage("Can't make move from player store!");
    }

    @Test
    void disallowMoveWhenPitIsEmpty() {
        assertThatExceptionOfType(IllegalMoveException.class)
                .isThrownBy(() -> new GameMaster(BoardFixture.firstPitEmpty()).validateMove(1))
                .withMessage("This pit is empty, can't make a move.");
    }

    @Test
    void shouldMakeMoveAndReturnBoard() {
        // given
        GameMaster gameMaster = new GameMaster(BoardFixture.initBoard());

        // when
        Board boardAfterMove = gameMaster.move(1);

        // then
        assertThat(boardAfterMove.getPits()).extracting(Pit::getValue)
                .containsExactly(0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0);
    }

    @Test
    void trueWhenEitherSideOfBoardIsEmpty() {
        GameMaster gameMaster = new GameMaster(BoardFixture.emptyPitsForNorth());

        assertThat(gameMaster.isEitherHouseSideIsEmpty()).isTrue();
    }
}
