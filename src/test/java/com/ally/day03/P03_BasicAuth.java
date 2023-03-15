package com.ally.day03;

import com.ally.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P03_BasicAuth extends SpartanAuthTestBase {

    /**
     * Role Based Access Control --> RBAC
     * <p>
     * admin   GET  POST  PUT  PATCH  DELETE
     * editor  GET  POST  PUT  PATCH   403
     * user    GET  403   403   403    403
     * guest   401  401   401   401    401
     */


    @Test
    public void negativeTestWithoutAuth() {

        given().accept(ContentType.JSON)
                .pathParam("id", 5)
                .when().get("/spartans/{id}").prettyPeek().
                then().statusCode(401)
                .body("error", is("Unauthorized"))

        ;   // Unauthorized user

    }


    @Test
    public void getSpartanAsUser() {

        given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .auth().basic("user", "user").
                when().get("/spartans/{id}").prettyPeek()
                .then().statusCode(200);
    }

    @Test
    public void deleteSpartanAsUser() {

        given().contentType(ContentType.JSON)
                .auth().basic("editor", "editor")
                .pathParam("id", 222).
                when().delete("/spartans/{id}").prettyPeek().
                then().statusCode(403)
                .body("error", is("Forbidden"));


    }
}
