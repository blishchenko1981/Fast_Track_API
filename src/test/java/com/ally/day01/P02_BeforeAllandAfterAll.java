package com.ally.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_BeforeAllandAfterAll { // add baseURI and add static imports for RestAssured and Assertions



    @BeforeAll
    public static void init() {

        baseURI = "http://3.91.96.199:1000";
        basePath = "/ords/hr";
    }

    @AfterAll
    public static void destroy() {
        reset();
    }

    @Test
    public void simpleTest() {


        Response response = get("/regions");
        // Response
        // print response and keep method chaining
        response.prettyPeek();
        assertEquals(200, response.statusCode());
    }


}
