package com.ally.day02;

import com.ally.utility.HrTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P04_HamcrestMatchers extends HrTestBase {

    @Test
    public void getAllRegions(){

        given().accept(ContentType.JSON).
                when().get("/regions").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .header("Date", notNullValue())
                .body("items[0].region_name", is("Europe"))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1,2,4,3))
        ;


    }
}
