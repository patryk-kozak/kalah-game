package com.softwaremind.pakz.kalah.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiResponse {

    private final long id;
    private final String url;
    private final Map<Integer, Integer> status;

}
