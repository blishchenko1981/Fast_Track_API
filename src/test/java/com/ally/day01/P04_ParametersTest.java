package com.ally.day01;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_ParametersTest extends SpartanTestBase {

    @DisplayName("GET/spartans/{id} with Path Param example")
    @Test
    public void pathParam() {

        Response response = given().log().all().accept(ContentType.JSON)
                .pathParam("id", 15).
                when().get("/spartans/{id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        int id = response.path("id");
        System.out.println("id = " + id);

        String name = response.path("name");
        System.out.println("name = " + name);

        String gender = response.path("gender");

        int phone = response.path("phone");
        System.out.println("phone = " + phone);



    }


}
