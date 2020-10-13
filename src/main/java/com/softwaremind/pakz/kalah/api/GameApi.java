package com.softwaremind.pakz.kalah.api;

import com.softwaremind.pakz.kalah.game.GameProvider;
import com.softwaremind.pakz.kalah.game.GameplayService;
import com.softwaremind.pakz.kalah.IllegalMoveException;
import com.softwaremind.pakz.kalah.model.Board;
import com.softwaremind.pakz.kalah.model.Pit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameApi {

    private final GameplayService gameplayService;
    private final GameProvider gameProvider;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse setup() {
        long gameId = gameProvider.prepareNewBoard();
        return ApiResponse.builder()
                .id(gameId)
                .url(processUrl(gameId))
                .build();
    }

    @PutMapping(value = "/{id}/pits/{pitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse move(@PathVariable("id") long gameId, @PathVariable int pitId) throws IllegalMoveException {
        Board updatedBoard = gameplayService.makeMove(gameId, pitId);
        return ApiResponse.builder()
                .id(gameId)
                .url(processUrl(gameId))
                .status(
                        updatedBoard.getPits().stream()
                                .collect(Collectors.toMap(Pit::getNumber, Pit::getValue))
                )
                .build();
    }

    private String processUrl(long gameId) {
        return ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/games/{gameId}")
                .buildAndExpand(gameId)
                .toUriString();
    }

}
