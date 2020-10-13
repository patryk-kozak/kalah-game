package com.softwaremind.pakz.kalah.api;

import com.softwaremind.pakz.kalah.BoardProvider;
import com.softwaremind.pakz.kalah.GameplayService;
import com.softwaremind.pakz.kalah.IllegalMoveException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GameApi.class)
@Import({GameplayService.class, BoardProvider.class, ApiExceptionHandler.class})
class ApiExceptionHandlerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GameplayService gameplayServiceMock;

    @Test
    void shouldWrapIllegalMoveExceptionWithApiError() throws Exception {
        given(gameplayServiceMock.makeMove(1234, 7))
                .willThrow(new IllegalMoveException("Can't make move from player store!"));

        mockMvc.perform(put("/games/1234/pits/7"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("Can't make move from player store!"));
    }

}
