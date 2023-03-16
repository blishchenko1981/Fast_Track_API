package com.ally.day03;

import com.ally.POJO.Spartan;
import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P02_SerializationPOJO extends SpartanTestBase {

    @Test
    public void postSpartan(){

        Spartan spartan = new Spartan();
        spartan.setName("POST POJO");
        spartan.setGender("Male");
        spartan.setPhone(1231231231);

        System.out.println("spartan = " + spartan);
        System.out.println("-----------------------------------------");
        given().log().body().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan).
                when()
                .post("/spartans").
        then().statusCode(201);


    }
}
