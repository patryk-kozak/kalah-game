package com.softwaremind.pakz.kalah.game;

import com.softwaremind.pakz.kalah.BoardFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameProviderTest {

    private final GameProvider gameProvider = new GameProvider();

    @Test
    void shouldCreateAndReturnIdOfNewGame() {
        assertThat(gameProvider.prepareNewBoard()).isEqualTo(1L);
    }

    @Test
    void shouldStoreCreatedGame() {
        long createdGameId = gameProvider.prepareNewBoard();

        assertThat(gameProvider.retrieveBoard(createdGameId))
                .usingRecursiveComparison()
                .isEqualTo(BoardFixture.initBoard());
    }
}
