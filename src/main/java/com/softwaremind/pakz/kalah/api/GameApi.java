package com.softwaremind.pakz.kalah.api;

import com.softwaremind.pakz.kalah.BoardProvider;
import com.softwaremind.pakz.kalah.GameplayService;
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
    private final BoardProvider boardProvider;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse setup() {
        long id = boardProvider.prepareNewBoard();
        return ApiResponse.builder()
                .id(id)
                .url(processUrl(id))
                .build();
    }

    @PutMapping(value = "/{id}/pits/{pitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse move(@PathVariable long id, @PathVariable int pitId) throws IllegalMoveException {
        Board updatedBoard = gameplayService.makeMove(id, pitId);
        return ApiResponse.builder()
                .id(id)
                .url(processUrl(id))
                .status(
                        updatedBoard.getPits().stream()
                                .collect(Collectors.toMap(Pit::getNumber, Pit::getValue))
                )
                .build();
    }

    private String processUrl(long id) {
        return ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/games/{id}")
                .buildAndExpand(id)
                .toUriString();
    }

}
