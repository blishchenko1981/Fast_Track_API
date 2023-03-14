package com.ally.day02;

import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class P06_JSON_to_Java extends SpartanTestBase {

//    {
//    "id": 2,
//    "name": "Nels",
//    "gender": "Male",
//    "phone": 4218971348
//}

    @Test
    public void convertJSON_to_collectionsTest() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2).

                when().get("/spartans/{id}").prettyPeek().

                then().statusCode(200).
                contentType(ContentType.JSON).extract().response();


        // first approach - use response
        Map<String, Object> spartan = response.path("");


        // second approach - use jsonPath
        Map<String, Object> mapResponse = response.jsonPath().getMap("");
        System.out.println("mapResponse = " + mapResponse);

        int id = (int) mapResponse.get("id");
        String name = (String) mapResponse.get("name");
        String gender = (String) mapResponse.get("gender");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);

    }

    @Test
    public void getAllSpartans() {

        Response response = given().accept(ContentType.JSON).
                when().get("/spartans").
                then().statusCode(200).contentType(ContentType.JSON)
                .extract().response();


        List<Map<String, Object>> listAllSpartans = response.jsonPath().getList("");
        int i = 1;
        for (Map<String, Object> eachSpartan : listAllSpartans) {

            System.out.println("Spartan " + i + " = " + eachSpartan);
            i = i+1;
        }

        System.out.println("listAllSpartans.get(0) = " + listAllSpartans.get(0));

        Map<String, Object> firstSpartan = listAllSpartans.get(0);

        String name = (String)firstSpartan.get("name");

        System.out.println("name = " + name);

    }

}
