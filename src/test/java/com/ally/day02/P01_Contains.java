package com.ally.day02;

import com.ally.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_Contains extends HrTestBase {

    @Test
    public void getSingleRegion() {

      Response response =  given().accept(ContentType.JSON)
                .and() // syntactic sugar just to increase readability of code
                .pathParam("id", 2).
                when().get("regions/{id}").prettyPeek();

        // Status code

        assertEquals(200, response.statusCode() ) ;

        assertEquals("application/json", response.contentType());

        assertTrue(response.asString().contains("Americas"));


    }

}
