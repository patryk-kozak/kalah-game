package com.softwaremind.pakz.kalah.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaremind.pakz.kalah.BoardFixture;
import com.softwaremind.pakz.kalah.game.GameProvider;
import com.softwaremind.pakz.kalah.game.GameplayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameApi.class)
@Import({GameplayService.class, GameProvider.class})
class GameApiIT {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GameplayService gameplayServiceMock;
    @MockBean
    private GameProvider gameProvider;

    @Test
    void setup() throws Exception {
        given(gameProvider.prepareNewBoard()).willReturn(1234L);

        mockMvc.perform(post("/games"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1234"))
                .andExpect(jsonPath("$.url").value("http://localhost/games/1234"));

        then(gameProvider).should().prepareNewBoard();
    }

    @Test
    void move() throws Exception {
        given(gameplayServiceMock.makeMove(1234, 1)).willReturn(BoardFixture.initBoard());

        mockMvc.perform(put("/games/1234/pits/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ApiResponseFixture.withStatus())));

        then(gameplayServiceMock).should().makeMove(1234, 1);
    }
}
