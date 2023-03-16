package com.ally.utility;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class BookitUtils {

    public static String getToken(String email, String password){

        String accessToken = given().contentType(ContentType.JSON)
                .queryParams("email", email)
                        .queryParam("password", password).
                when().get("/sign").prettyPeek().
                then().statusCode(200)
                .extract().jsonPath().getString("accessToken");

        return "Bearer " + accessToken;
    }
}
