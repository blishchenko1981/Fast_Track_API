package com.ally.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class HrTestBase {


    @BeforeAll
    public static void init() {

        baseURI = "http://3.91.96.199:1000";
        basePath = "/ords/hr";
    }

    @AfterAll
    public static void destroy() {
        reset();
    }
}



