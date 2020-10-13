package com.softwaremind.pakz.kalah.model;

import com.softwaremind.pakz.kalah.PitFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitTest {

    @Test
    void add() {
        Pit pit = PitFixture.sixStonePit(1);

        pit.add();

        assertThat(pit.getValue()).isEqualTo(7);
    }

    @Test
    void clear() {
        Pit pit = PitFixture.sixStonePit(1);

        pit.clear();

        assertThat(pit.getValue()).isZero();
    }

    @Test
    void addValue() {
        Pit pit = PitFixture.playerStoreWithValue(1, 22);
        pit.addValue(5);
        assertThat(pit.getValue()).isEqualTo(27);
    }
}
