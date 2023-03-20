package com.ally.day04;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class P05_MOCK_Api {

    //https://b4fdd966-bc7e-436e-ab6e-18b301ca9c77.mock.pstmn.io
    @BeforeAll
    public static void init(){
        baseURI = "https://b4fdd966-bc7e-436e-ab6e-18b301ca9c77.mock.pstmn.io";

    }



    @Test
    public void test1(){

given().accept(ContentType.JSON).log().all().
        when().get("/spartans").prettyPeek().
then()
        .contentType(ContentType.JSON)
        .statusCode(200)
        .body("id", everyItem(notNullValue()))
        // TO VALIDATE SCHEMA NEED TO PUT SCHEMA AS A FILE IN RESOURCES
     //   .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("***NAME_OF_THE_FILE_FROM_RESOURCES.json***"))
;

    }


}
