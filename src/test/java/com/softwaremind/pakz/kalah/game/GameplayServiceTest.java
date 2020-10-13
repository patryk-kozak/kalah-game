package com.softwaremind.pakz.kalah.game;

import com.softwaremind.pakz.kalah.BoardFixture;
import com.softwaremind.pakz.kalah.IllegalMoveException;
import com.softwaremind.pakz.kalah.game.GameProvider;
import com.softwaremind.pakz.kalah.game.GameplayService;
import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameplayServiceTest {

    @Mock
    private GameProvider gameProviderMock;

    @InjectMocks
    private GameplayService gameplayService;

    @Test
    void shouldMakeMoveAndReturnBoard() throws IllegalMoveException {
        // given
        long gameId = 1L;
        given(gameProviderMock.retrieveBoard(gameId)).willReturn(BoardFixture.initBoard());

        // when
        List<Pit> pits = gameplayService.makeMove(gameId, 1).getPits();

        // then
        assertThat(pits).extracting(Pit::getValue)
                .containsExactly(0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0);
    }

    @Test
    void finishAndSumUpScore() throws IllegalMoveException {
        long gameId = 11235;
        given(gameProviderMock.retrieveBoard(gameId)).willReturn(
                BoardFixture.lastPitForSouth()
        );

        Board board = gameplayService.makeMove(gameId, 6);

        assertThat(board.getPits()).extracting(Pit::getValue)
                .containsExactly(0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 54);
    }

    @Test
    void makeMoveFromNorthSide() throws IllegalMoveException {
        long gameId = 11235;
        given(gameProviderMock.retrieveBoard(gameId)).willReturn(
                BoardFixture.lastPitForSouth()
        );

        Board board = gameplayService.makeMove(gameId, 11);

        assertThat(board.getPits()).extracting(Pit::getValue)
                .containsExactly(1, 1, 1, 1, 1, 2, 17, 0, 2, 1, 0, 5, 3, 37);
    }


}
