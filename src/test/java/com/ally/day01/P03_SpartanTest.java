package com.ally.day01;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class P03_SpartanTest extends SpartanTestBase {

    @Test
    public void getAllSpartans() {

        Response response = given().log().all().accept(ContentType.JSON)
                .when().get("/spartans");


        // Response
        System.out.println("-----------------------------------------------------------------------");
       // response.prettyPrint();
        // Content-Type
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Status Code
        assertEquals(HttpStatus.SC_OK, response.statusCode());


        // Get first saprtan gender
        System.out.println("response.path(\"[0].gender\").toString() = " + response.path("[0].gender").toString());
        System.out.println("response.body().jsonPath().getList(\"name\").toString() = " + response.body().jsonPath().getList("gender").toString());


        // Get first spartan name
        System.out.println("response.path(\"[0].name\") = " + response.path("[0].name"));

        // Get all spartan names
        System.out.println("response.path(\"name\") = " + response.path("name"));
    }

}
