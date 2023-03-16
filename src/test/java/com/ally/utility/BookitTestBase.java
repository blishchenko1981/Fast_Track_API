package com.ally.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;


public abstract class BookitTestBase {



    @BeforeAll
    public static void init(){

        baseURI = "https://api.qa.bookit.cydeo.com";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

}
