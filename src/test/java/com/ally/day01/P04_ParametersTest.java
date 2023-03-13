package com.ally.day01;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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


    @Test
    public void queryParam(){

        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "it");
        queryMap.put("gender", "Female");

      Response response =   given().accept(ContentType.JSON).log().all()
              .queryParams(queryMap).
               // .queryParam("nameContains", "it")
               // .queryParam("gender", "Female").

                when().get("/spartans/search").prettyPeek();


        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));

        System.out.println("response all ID  = " + response.path("content.id"));

        // Get last name:
        System.out.println("response last name = " + response.path("content[-1].name"));
        assertEquals(200, response.statusCode());
    }

    @Test
    public void negativeTest(){
       Response response =  given().accept(ContentType.JSON)
                .pathParam("id" , 5000).
                when().get("/spartans/{id}").prettyPeek();

       assertEquals(404, response.statusCode());

       // verify content type is application/json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        //contains
       assertTrue( response.asString().contains("Not Found"));

       // check individually

        String error = response.path("error");
        assertEquals("Not Found", error);
    }


}
