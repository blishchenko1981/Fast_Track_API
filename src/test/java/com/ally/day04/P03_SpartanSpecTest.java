package com.ally.day04;

import com.ally.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P03_SpartanSpecTest extends SpartanAuthTestBase {


    @Test
    public void test1() {
        //Request Specification

                 given().spec(reqSpec("user", "user")).
                 when().get("/spartans").
                 then().spec(respSpec(200));
    }


    @Test
    public void test2() {





        given().spec(reqSpec("admin", "admin"))
                        .pathParam("id", 2).
                when().get("/spartans/{id}").
                then().spec(respSpec(200)).log().all();
    }
}
