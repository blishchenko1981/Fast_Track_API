package com.ally.day03;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P01_SerializationMap extends SpartanTestBase {

    @Test
    public void postSpartan(){
        // verify success message

        Map<String, Object> spartanMap = new HashMap<>();

        spartanMap.put("name", "Vitalii");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 12345654332l);

        System.out.println("spartanMap = " + spartanMap);

       int spartanID = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)   // send data with POST request
                .body(spartanMap)             // Serialization happens here to convert map to Json
                .when().post("/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath().getInt("data.id");

        System.out.println("Spartan created !!!");
    }


    @Test

    public void putSpartan(){

    }

}
