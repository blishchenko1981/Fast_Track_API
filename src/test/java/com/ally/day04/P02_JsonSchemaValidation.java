package com.ally.day04;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P02_JsonSchemaValidation {

    @Test
    public void validation1() {
        given().accept(ContentType.JSON)
                .pathParam("id", 2).
         when().get("spartans/{id}").prettyPeek().
         then().statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("")) // dont have
        ;

    }

    @Test
    public void validation2() {
        given().accept(ContentType.JSON)
                .pathParam("id", 2).
                when().get("spartans/{id}").prettyPeek().
                then().statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json"))) // dont have
        ;

    }
}
