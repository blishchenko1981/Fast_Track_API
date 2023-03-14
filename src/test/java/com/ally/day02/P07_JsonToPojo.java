package com.ally.day02;

import com.ally.POJO.Search;
import com.ally.POJO.Spartan;
import com.ally.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P07_JsonToPojo extends SpartanTestBase {

    //    {
//    "id": 2,
//    "name": "Nels",
//    "gender": "Male",
//    "phone": 4218971348
//}
    @Test
    public void getSpartan() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2).

                when().get("/spartans/{id}").

                then().statusCode(200).
                contentType(ContentType.JSON).extract().response();

        System.out.println("response.path(\"\") = " + response.path(""));

        System.out.println(" _________ First Approach to get a Object from Response ___________-______");
        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);

        System.out.println(" _________ Second Approach to get a Object from Response ___________-______");

        JsonPath jsonPath = response.jsonPath();
        Spartan spart1 = jsonPath.getObject("", Spartan.class);


    }

    @Test
    public void searchSpartans() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("nameContains", "f")
                .queryParam("gender", "Female").
                when().get("/spartans/search").prettyPeek().
                then().statusCode(200).extract().response();


        // GET First Spartan as Spartan:
        // response.as("content[0]", Spartan.class) will complain since no such method

        JsonPath jp = response.jsonPath();
        Spartan spartan = jp.getObject("content[0]", Spartan.class);
        System.out.println("spartan.get First Name = " + spartan.getName());


    }

    @Test
    public void searchSpartanPOJO() {
        Response response = given()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "it").

                when().get("/spartans/search").prettyPeek().

                then()
                .log().ifValidationFails() // if any validation fails then() by using HamCrest it will log detail
                .statusCode(200).extract().response();


        JsonPath jp = response.jsonPath();
        Search search = jp.getObject("", Search.class);
        System.out.println("search.getTotalElement() = " + search.getTotalElement());

//        List<Spartan> listSpartans = search.getContent();
//        System.out.println("listSpartans.size() = " + listSpartans.size());
//
//        Spartan first_spartan  = listSpartans.get(0);
//        System.out.println("first_spartan.getName() = " + first_spartan.getName());
//        System.out.println("first_spartan.getGender() = " + first_spartan.getGender());
//        System.out.println("first_spartan.getPhone() = " + first_spartan.getPhone());

        int numberOfSpartans = search.getAllSpartans().size();
        System.out.println("numberOfSpartans = " + numberOfSpartans);

        System.out.println("search.getAllSpartans().get(0) = " + search.getAllSpartans().get(0));

        for (Spartan each : search.getAllSpartans()) {
            System.out.println("each.toString() = " + each.toString());
        }

    }
}
