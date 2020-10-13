package com.softwaremind.pakz.kalah;

import com.softwaremind.pakz.kalah.model.Pit;
import com.softwaremind.pakz.kalah.model.PitType;

import java.util.List;
import java.util.stream.IntStream;

public class PitFixture {

    public static List<Pit> initPits() {
        return List.of(
                sixStonePit(1),
                sixStonePit(2),
                sixStonePit(3),
                sixStonePit(4),
                sixStonePit(5),
                sixStonePit(6),
                playerStore(7),
                sixStonePit(8),
                sixStonePit(9),
                sixStonePit(10),
                sixStonePit(11),
                sixStonePit(12),
                sixStonePit(13),
                playerStore(14)
        );
    }

    public static Pit sixStonePit(int id) {
        return new Pit(id, PitType.HOUSE);
    }

    public static Pit customValuePit(int id, int value, PitType type) {
        Pit pit = new Pit(id, type);
        pit.clear();
        IntStream.range(0, value).forEach(i -> pit.add());
        return pit;
    }

    public static Pit playerStore(int id) {
        Pit store = new Pit(id, PitType.STORE);
        store.clear();
        return store;
    }

    public static Pit playerStoreWithValue(int id, int initValue) {
        Pit store = new Pit(id, PitType.STORE);
        store.clear();
        IntStream.range(0, initValue).forEach(i -> store.add());
        return store;
    }
}
