package com.softwaremind.pakz.kalah;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardProviderTest {

    private final BoardProvider boardProvider = new BoardProvider();

    @Test
    void shouldCreateAndReturnIdOfNewGame() {
        assertThat(boardProvider.prepareNewBoard()).isEqualTo(1L);
    }

    @Test
    void shouldStoreCreatedGame() {
        long createdGameId = boardProvider.prepareNewBoard();

        assertThat(boardProvider.retrieveBoard(createdGameId))
                .usingRecursiveComparison()
                .isEqualTo(BoardFixture.initBoard());
    }
}
