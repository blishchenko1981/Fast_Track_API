package com.ally.utility;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class SpartanAuthTestBase {


    @BeforeAll
    public static void init() {

        baseURI = "http://3.91.96.199:7000";
        basePath = "/api";
    }

    @AfterAll
    public static void destroy() {
        reset();
    }



    //  RequestSpecification requestSpec = given().accept(ContentType.JSON)
    //                        .auth().basic("admin", "admin").pathParam("id", 2);
    //
    //        ResponseSpecification responseSpec = expect().contentType(ContentType.JSON).statusCode(200);


    public static RequestSpecification reqSpec(String username, String password){
        return given().accept(ContentType.JSON)
                                .auth().basic(username, password);
    }

    public static ResponseSpecification respSpec(int statusCode){

        return expect().contentType(ContentType.JSON).statusCode(statusCode);
    }
}

