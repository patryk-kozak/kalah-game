package com.softwaremind.pakz.kalah.api;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseFixture {

    private static final Map<Integer, Integer> status = new HashMap<>() {{
        put(1, 6);
        put(2, 6);
        put(3, 6);
        put(4, 6);
        put(5, 6);
        put(6, 6);
        put(7, 0);
        put(8, 6);
        put(9, 6);
        put(10, 6);
        put(11, 6);
        put(12, 6);
        put(13, 6);
        put(14, 0);
    }};

    public static ApiResponse withStatus() {
        return new ApiResponse(1234, "http://localhost/games/1234", status);
    }
}
