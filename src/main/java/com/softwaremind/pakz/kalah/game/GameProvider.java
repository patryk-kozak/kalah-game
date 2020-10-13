package com.softwaremind.pakz.kalah.game;

import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import com.softwaremind.pakz.kalah.model.PitType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class GameProvider {

    private final Map<Long, Board> games = new HashMap<>();

    public long prepareNewBoard() {
        long newGameId = games.keySet().size() + 1L;

        List<Pit> pits = new LinkedList<>();
        IntStream.range(0, 14).forEach(i -> pits.add(i, new Pit(i + 1, PitType.HOUSE)));

        Pit northStore = new Pit(7, PitType.STORE);
        northStore.clear();
        pits.set(6, northStore);

        Pit southStore = new Pit(14, PitType.STORE);
        southStore.clear();
        pits.set(13, southStore);

        games.put(newGameId, new Board(pits));
        return newGameId;
    }

    public Board retrieveBoard(long gameId) {
        return games.get(gameId);
    }


}
