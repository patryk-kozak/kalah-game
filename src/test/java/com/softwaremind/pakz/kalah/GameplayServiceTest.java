package com.softwaremind.pakz.kalah;

import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameplayServiceTest {

    @Mock
    private BoardProvider boardProviderMock;

    @InjectMocks
    private GameplayService gameplayService;

    @Test
    void shouldMakeMoveAndReturnBoard() throws IllegalMoveException {
        // given
        long gameId = 1L;
        given(boardProviderMock.retrieveBoard(gameId)).willReturn(BoardFixture.initBoard());

        // when
        List<Pit> pits = gameplayService.makeMove(gameId, 1).getPits();

        // then
        assertThat(pits).extracting(Pit::getValue)
                .containsExactly(0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0);
    }

    @Test
    void disallowMoveWhenPitIsStore() {
        // given
        long gameId = 1L;
        given(boardProviderMock.retrieveBoard(gameId)).willReturn(BoardFixture.initBoard());

        assertThatExceptionOfType(IllegalMoveException.class)
                .isThrownBy(() -> gameplayService.makeMove(gameId, 7))
                .withMessage("Can't make move from player store!");
    }

    @Test
    void disallowMoveWhenPitIsEmpty() {
        long gameId = 1L;
        given(boardProviderMock.retrieveBoard(gameId)).willReturn(BoardFixture.firstPitEmpty());

        assertThatExceptionOfType(IllegalMoveException.class)
                .isThrownBy(() -> gameplayService.makeMove(gameId, 1))
                .withMessage("This pit is empty, can't make a move.");
    }

    @Test
    void finishAndSumUpScore() throws IllegalMoveException {
        long gameId = 11235;
        given(boardProviderMock.retrieveBoard(gameId)).willReturn(
                BoardFixture.lastPitForSouth()
        );

        Board board = gameplayService.makeMove(gameId, 6);

        assertThat(board.getPits()).extracting(Pit::getValue)
                .containsExactly(0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 54);
    }

    @Test
    void makeMoveFromNorthSide() throws IllegalMoveException {
        long gameId = 11235;
        given(boardProviderMock.retrieveBoard(gameId)).willReturn(
                BoardFixture.lastPitForSouth()
        );

        Board board = gameplayService.makeMove(gameId, 11);

        assertThat(board.getPits()).extracting(Pit::getValue)
                .containsExactly(1, 1, 1, 1, 1, 2, 17, 0, 2, 1, 0, 5, 3, 37);
    }


}
