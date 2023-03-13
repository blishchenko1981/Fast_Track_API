package com.ally.day02;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

// instead of Assertions class we will use HamcrestMatchers
import static org.hamcrest.Matchers.*;


public class P05_SpartanTest extends SpartanTestBase {

    @Test
    public void searchSpartans(){

        given().log().all()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "it").

                when().get("/spartans/search").prettyPeek().

                then()
                .log().ifValidationFails() // if any validation fails then() by using HamCrest it will log detail
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .body("totalElement", is(2))
                .body("content", hasSize(2))
                .body("content.name", hasItem("Amalita"))
                .body("content.gender", everyItem(is("Female")));

    }

}
