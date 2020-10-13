package com.softwaremind.pakz.kalah.game;

import com.softwaremind.pakz.kalah.IllegalMoveException;
import com.softwaremind.pakz.kalah.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameplayService {

    private final GameProvider gameProvider;

    public Board makeMove(long gameId, int pitId) throws IllegalMoveException {

        GameMaster gameMaster = new GameMaster(gameProvider.retrieveBoard(gameId));
        gameMaster.validateMove(pitId);

        Board board = gameMaster.move(pitId);

        if (gameMaster.isEitherHouseSideIsEmpty()) {
            board.finish();
            board.sumUpScore();
        }

        return board;
    }

}
