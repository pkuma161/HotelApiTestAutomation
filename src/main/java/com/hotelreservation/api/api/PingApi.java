package com.hotelreservation.api.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PingApi extends BaseApi {
    protected static final String PING_ENDPOINT = BASE_ENDPOINT + "ping/";

    public static Response healthCheck() {
        return given()
                .get(PING_ENDPOINT);
    }
}
