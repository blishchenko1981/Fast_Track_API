package com.ally.day03;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P01_SerializationMap extends SpartanTestBase {

   static int spartanID ;

    @Order(1)
    @Test
    public void postSpartan(){
        // verify success message

        Map<String, Object> spartanMap = new HashMap<>();

        spartanMap.put("name", "Vitalii");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 12345654332l);

        System.out.println("spartanMap = " + spartanMap);

        spartanID = given().accept(ContentType.JSON)
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

@Order(2)
    @Test
    public void putSpartan(){
        Map<String, Object> spartanMap = new HashMap<>();

        spartanMap.put("name", "VitaliiPUT");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 12345654332l);

        given().contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(spartanMap).
        when().put("spartans/{id}").
                then().statusCode(204);

        System.out.println(spartanID + " Is Updated");

    }

@Order(3)
    @Test
    public void patchSpartan(){

        Map<String, Object > patchMap = new HashMap<>();

        patchMap.put("name", "ImCare");

        given().contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(patchMap).
                when().patch("/spartans/{id}").
                then().statusCode(204);
        // -------------------------------------------------------------
       Response response =  given().pathParam("id", spartanID).
               when().get("/spartans/{id}");

             assertEquals("ImCare",   response.path("name") );
        ;

    }
@Order(4)
    @Test
    public void createAndDeleteSpartan(){

      //  postSpartan();

        System.out.println("spartanID = " + spartanID);
        given().pathParam("id", spartanID ).log().all().
                when().delete("/spartans/{id}").
                then().statusCode(204);

        System.out.println("Spartan Is deleted");
    }

    @Order(5)
    @Test
    public void chechIfDeleted(){
        when().get("/spartans/"+spartanID).then().statusCode(404);

        System.out.println(spartanID + " Spartan was deleted, just made sure");
    }

}
